package com.kamalan.databasetester.model;

import com.google.gson.GsonBuilder;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by hesam on 9/5/16.
 */
@RealmClass
public class Booking implements RealmModel {

    @PrimaryKey
    private String id;
    private String phoneNumber;
    private String code;
    private String taxiTypeId;
    private String requestedTaxiTypeName;
    private Long pickUpTime;
    private Float fareLowerBound = 0.0f;
    private Float fareUpperBound = 0.0f;

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, Booking.class);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTaxiTypeId() {
        return taxiTypeId;
    }

    public void setTaxiTypeId(String taxiTypeId) {
        this.taxiTypeId = taxiTypeId;
    }

    public String getRequestedTaxiTypeName() {
        return requestedTaxiTypeName;
    }

    public void setRequestedTaxiTypeName(String requestedTaxiTypeName) {
        this.requestedTaxiTypeName = requestedTaxiTypeName;
    }

    public Long getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(Long pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public Float getFareLowerBound() {
        return fareLowerBound;
    }

    public void setFareLowerBound(Float fareLowerBound) {
        this.fareLowerBound = fareLowerBound;
    }

    public Float getFareUpperBound() {
        return fareUpperBound;
    }

    public void setFareUpperBound(Float fareUpperBound) {
        this.fareUpperBound = fareUpperBound;
    }
}
