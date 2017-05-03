package com.datamining.entity;

/**
 * Created by wanghuali1 on 2017/4/5.
 */
public class SelectedProductInfo {
    private final static String DELIMETER = new String(new byte[]{1});
    public String pmid;
    public String prodid;
    public String saleMerchantId;
    public String saleFlag;
    public String showFlag;
    public String deleteFlag;
    public String giftFlag;
    public String brandid;
    public String categoryId;
    public String createTime;

    public SelectedProductInfo(String value) {

        String[] productsInfo = value.split("[\t,]");
        this.pmid = productsInfo[0];
        this.prodid = productsInfo[1];
        this.saleMerchantId = productsInfo[2];
        this.saleFlag = productsInfo[3];
        this.showFlag = productsInfo[4];
        this.deleteFlag = productsInfo[5];
        this.giftFlag = productsInfo[6];
        this.brandid = productsInfo[7];
        this.categoryId = productsInfo[8];
    }

    public String getPmid() {
        return pmid;
    }

    public void setPmid(String pmid) {
        this.pmid = pmid;
    }

    public String getProdid() {
        return prodid;
    }

    public void setProdid(String prodid) {
        this.prodid = prodid;
    }

    public String getSaleMerchantId() {
        return saleMerchantId;
    }

    public void setSaleMerchantId(String saleMerchantId) {
        this.saleMerchantId = saleMerchantId;
    }

    public String getSaleFlag() {
        return saleFlag;
    }

    public void setSaleFlag(String saleFlag) {
        this.saleFlag = saleFlag;
    }

    public String getShowFlag() {
        return showFlag;
    }

    public void setShowFlag(String showFlag) {
        this.showFlag = showFlag;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getGiftFlag() {
        return giftFlag;
    }

    public void setGiftFlag(String giftFlag) {
        this.giftFlag = giftFlag;
    }

    public String getBrandid() {
        return brandid;
    }

    public void setBrandid(String brandid) {
        this.brandid = brandid;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return saleMerchantId+"\t"+categoryId + "\t" + prodid;

    }
}
