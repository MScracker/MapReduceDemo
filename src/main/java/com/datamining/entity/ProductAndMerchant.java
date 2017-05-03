package com.datamining.entity;

import java.util.*;

/**
 * Created by wanghuali1 on 2017/4/12.
 */
public class ProductAndMerchant {
    public String productId;
    public String MerchantId;

    public ProductAndMerchant(String productId, String merchantId) {
        this.productId = productId;
        MerchantId = merchantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(String merchantId) {
        MerchantId = merchantId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        boolean flag = (((ProductAndMerchant) obj).getProductId().equals(this.productId))
                && (((ProductAndMerchant) obj).getMerchantId().equals(this.MerchantId));
        System.out.println(flag);
        return flag;
    }

    @Override
    public String toString() {
        return productId + "\t" + MerchantId;
    }

    public static void main(String[] args) {
        List<ProductAndMerchant> productInfoList = new ArrayList<ProductAndMerchant>();
        ProductAndMerchant pm1 = new ProductAndMerchant("1", "292");
        ProductAndMerchant pm2 = new ProductAndMerchant("2", "292");
        ProductAndMerchant pm3 = new ProductAndMerchant("1", "292");
        ProductAndMerchant pm4 = new ProductAndMerchant("5", "292");
//        productInfoList.add(pm1);
//        productInfoList.add(pm2);
//        productInfoList.add(pm3);
//        productInfoList.add(pm4);

        if (!productInfoList.contains(pm1)) {
            productInfoList.add(pm1);
        }
        if (!productInfoList.contains(pm2)) {
            productInfoList.add(pm2);
        }
        if (!productInfoList.contains(pm3)) {
            productInfoList.add(pm3);
        }
        if (!productInfoList.contains(pm4)) {
            productInfoList.add(pm4);
        }
        for (ProductAndMerchant p : productInfoList) {
            System.out.println(p.toString());
//            System.out.println(p.getKey().toString()+"->"+p.getValue());
        }
    }
}
