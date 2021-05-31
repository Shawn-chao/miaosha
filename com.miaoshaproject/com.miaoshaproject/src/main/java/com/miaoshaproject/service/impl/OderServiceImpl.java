package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDOMapper;
import com.miaoshaproject.dao.SequenceDOMapper;
import com.miaoshaproject.dataobject.OrderDO;
import com.miaoshaproject.dataobject.SequenceDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName OderServiceImpl
 * @Description TODO
 * @date 2021/5/26 11:23
 * @Version 1.0
 */
@Service
public class OderServiceImpl implements OrderService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer promoId,Integer amount) throws BusinessException {
        //校验商品存在，用户合法，购买数量是否正确
        ItemModel itemModel=itemService.getItemById(itemId);
        if (itemModel==null){
            throw new BusinessException("商品信息不存在", EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        UserModel userModel=userService.getUserById(userId);
        if (userModel==null){
            throw new BusinessException("用户信息不存在", EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        if (amount<0||amount>99){
            throw new BusinessException("数量信息不正确",EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        //校验活动信息
        if (promoId!=null){
            //(1)校验对应活动是否存在这个适用商品
            if (promoId.intValue()!=itemModel.getPromoModel().getId()){
                throw new BusinessException("活动信息不正确",EmBusinessError.PARAMETER_VALIDATION_ERROR);
            }else if(itemModel.getPromoModel().getStatus().intValue()!=2){
                throw new BusinessException("活动还没开始呢！",EmBusinessError.PARAMETER_VALIDATION_ERROR);
            }
        }

        //落单减库存
        boolean result=itemService.decreaseStock(itemId,amount);
        if (!result){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //订单入库
        OrderModel orderModel=new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        if (promoId!=null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice()*amount);

        //生成交易流水号，订单号
        orderModel.setId(generateOrderNo());
        OrderDO orderDO=convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //加上销量
        itemService.increaseSales(itemId,amount);

        //返回前端
        return orderModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo(){
        //订单号有16位
        StringBuffer stringBuffer=new StringBuffer();

        //前8位为时间信息
        LocalDateTime date=LocalDateTime.now();
        String datetime=date.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuffer.append(datetime);

        //中间6为为自增序列
        //获取当前sequence
        int sequence=0;
        SequenceDO sequenceDO=sequenceDOMapper.getSequenceByName("order_info");
        sequence=sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);

        String sequencestr=String.valueOf(sequence);
        for (int i=0;i<6-sequencestr.length();i++){
            stringBuffer.append(0);
        }
        stringBuffer.append(sequencestr);

        //最后两位为分库分表位，暂时写死
        stringBuffer.append("00");

        return stringBuffer.toString();
    }

    private OrderDO convertFromOrderModel(OrderModel orderModel){
        if (orderModel==null){
            return null;
        }
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        return orderDO;
    }
}
