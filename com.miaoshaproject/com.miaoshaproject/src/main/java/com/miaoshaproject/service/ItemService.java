package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @ClassName ItemService
 * @Description TODO
 * @date 2021/5/24 19:08
 * @Version 1.0
 */
@Component
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException, InvocationTargetException, IllegalAccessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //库存扣减
    boolean decreaseStock(Integer itemId,Integer amount);

    //商品销量增加
    void increaseSales(Integer itemId,Integer amount);
}
