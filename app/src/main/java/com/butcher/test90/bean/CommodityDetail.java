package com.butcher.test90.bean;


/**
 * 一个实体类，也可以叫 bean 类，用来描述一个实体的属性, 可以用作泛型<br/>
 */
public class CommodityDetail {

    /**
     * canBuy : 8
     * product_id : 1
     * product_name : 我是第一件商品
     * money : 88
     * quantity : 2
     * msg : 颜色:红色
     * img_color : #FF0000
     */
    public int canBuy;
    public int product_id;
    public String product_name;
    public int money;
    public int quantity;
    public String msg;
    public String img_color;

    public int getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(int canBuy) {
        this.canBuy = canBuy;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg_color() {
        return img_color;
    }

    public void setImg_color(String img_color) {
        this.img_color = img_color;
    }
}
