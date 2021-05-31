package com.miaoshaproject.service.model;

import org.joda.time.DateTime;

/**
 * @ClassName PromoModel
 * @Description TODO
 * @date 2021/5/28 16:05
 * @Version 1.0
 */
public class PromoModel {
    private Integer id;

    //秒杀活动状态:1还没开始；2进行中；3已结束
    private Integer status;

    //秒杀活动名称
    private String promoName;

    //秒杀活动开始的时间
    private DateTime startDate;

    //秒杀活动结束时间
    private DateTime endDate;

    private Integer itemId;

    //秒杀活动的商品价格
    private Double promoItemPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Double getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(Double promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }
}
