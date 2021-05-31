package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

/**
 * @ClassName OrderService
 * @Description TODO
 * @date 2021/5/26 11:21
 * @Version 1.0
 */
public interface OrderService {
    //推荐使用1，通过前端url上传过来的秒杀活动id，然后下单接口内校验对应id是否属于对应商品且活动已开始
    //2，直接在下单接口内判断对应的商品是否存在秒杀活动，若存在进行中的以秒杀价格下单
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId,Integer amount) throws BusinessException;
}
