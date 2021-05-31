package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;
import org.springframework.stereotype.Component;

/**
 * @ClassName PromoService
 * @Description TODO
 * @date 2021/5/28 16:25
 * @Version 1.0
 */
@Component
public interface PromoService {
    //根据itemId获取即将进行或者正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);
}
