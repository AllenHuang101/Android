package com.asus.recyclercardview;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Allen on 2018/1/2.
 */

public class PointSummary {
    @SerializedName("PointDate")
    private String mPointDate;

    @SerializedName("SaleRecordPoint")
    private int mSaleRecordPoint;

    @SerializedName("UniversityPoint")
    private int mUniversityPoint;

    public PointSummary(String pointDate, int saleRecordPoint, int universityPoint) {
        this.mPointDate = pointDate;
        this.mSaleRecordPoint = saleRecordPoint;
        this.mUniversityPoint = universityPoint;
    }

    public String getPointDate() {
        return mPointDate;
    }

    public void setPointDate(String pointDate) {
        this.mPointDate = pointDate;
    }

    public int getSaleRecordPoint() {
        return mSaleRecordPoint;
    }

    public void setSaleRecordPoint(int saleRecordPoint) {
        this.mSaleRecordPoint = saleRecordPoint;
    }

    public int getUniversityPoint() {
        return mUniversityPoint;
    }

    public void setUniversityPoint(int universityPoint) {
        this.mUniversityPoint = universityPoint;
    }
}
