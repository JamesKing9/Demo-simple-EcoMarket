package com.butcher.test90.bean;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfo {

    private List<CommodityDetail> cartlist;

    public List<CommodityDetail> getCartlist() {
        if (cartlist == null)
            cartlist = new ArrayList<CommodityDetail>();
        return cartlist;
    }

    public void setCartlist(List<CommodityDetail> cartlist) {
        this.cartlist = cartlist;
    }
}
