package com.miaoshaproject.service.model;

/**
 * @ClassName OrderModel
 * @Description TODO
 * @date 2021/5/26 11:06
 * @Version 1.0
 */
//用户下单的交易模型
public class OrderModel {

    private String id;

    private Integer userId;

    private Integer itemId;

    //非空代表以秒杀商品的方式下单
    private Integer promoId;

    //购买商品的单价
    private double itemPrice;

    private Integer amount;

    //购买金额
    private double orderPrice;

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getOrderAmount() {
        return orderPrice;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderPrice = orderAmount;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}
