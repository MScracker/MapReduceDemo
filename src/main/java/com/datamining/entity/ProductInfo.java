package com.datamining.entity;

/**
 * Created by wanghuali1 on 2017/4/27.
 */
public class ProductInfo {
    private final String DELIMITER = new String(new byte[]{1});
    String prodId;
    String prodType;
    String bisinessType;
    String merchantId;
    String categLvlId;
    String categLvl3Id;

    public ProductInfo(String line) {
        String[] tokens = line.split(DELIMITER);
        this.prodId = tokens[0];
        this.prodType = tokens[1];
        this.bisinessType = tokens[2];
        this.merchantId = tokens[3];
        this.categLvlId = tokens[4];
        this.categLvl3Id = tokens[5];
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getBisinessType() {
        return bisinessType;
    }

    public void setBisinessType(String bisinessType) {
        this.bisinessType = bisinessType;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCategLvlId() {
        return categLvlId;
    }

    public void setCategLvlId(String categLvlId) {
        this.categLvlId = categLvlId;
    }

    public String getCategLvl3Id() {
        return categLvl3Id;
    }

    public void setCategLvl3Id(String categLvl3Id) {
        this.categLvl3Id = categLvl3Id;
    }
}
