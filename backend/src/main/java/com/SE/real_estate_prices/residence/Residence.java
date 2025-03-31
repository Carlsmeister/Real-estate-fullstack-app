package com.SE.real_estate_prices.residence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "residence_stats")
public class Residence {
    @Id
    @Column(name = "residence_address_id", unique = true, nullable = false)
    private Integer residenceAddressId;

    @Column(name = "published")
    private java.sql.Date published;

    @Column(name = "residence_type")
    private String residenceType;

    @Column(name = "asking_price")
    private BigDecimal askingPrice;

    @Column(name = "land_area_sqm")
    private Double landAreaSqm;

    @Column(name = "living_area_sqm")
    private Double livingAreaSqm;

    @Column(name = "sqm_price_sek")
    private BigDecimal sqmPriceSek;

    @Column(name = "number_of_rooms")
    private Double numberOfRooms;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "coordinates")
    private String coordinates;

    public Residence() {
    }

    public Residence(Integer residenceAddressId, java.sql.Date published, String residenceType, BigDecimal askingPrice, Double landAreaSqm, Double livingAreaSqm, BigDecimal sqmPriceSek, Double numberOfRooms, String address, String city, float coordinates) {
        this.residenceAddressId = residenceAddressId;
        this.published = published;
        this.residenceType = residenceType;
        this.askingPrice = askingPrice;
        this.landAreaSqm = landAreaSqm;
        this.livingAreaSqm = livingAreaSqm;
        this.sqmPriceSek = sqmPriceSek;
        this.numberOfRooms = numberOfRooms;
        this.address = address;
        this.city = city;
        this.coordinates = String.valueOf(coordinates);
    }

    public Residence(Integer residenceAddressId) {
        this.residenceAddressId = residenceAddressId;
    }

    public int getResidenceAddressId() {
        return residenceAddressId;
    }

    public void setResidenceAddressId(Integer residenceAddressId) {
        this.residenceAddressId = residenceAddressId;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(java.sql.Date published) {
        this.published = published;
    }

    public BigDecimal getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(BigDecimal askingPrice) {
        this.askingPrice = askingPrice;
    }

    public double getLandAreaSqm() {
        return landAreaSqm;
    }

    public void setLandAreaSqm(Double landAreaSqm) {
        this.landAreaSqm = landAreaSqm;
    }

    public double getLivingAreaSqm() {
        return livingAreaSqm;
    }

    public void setLivingAreaSqm(Double livingAreaSqm) {
        this.livingAreaSqm = livingAreaSqm;
    }

    public double getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Double numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public BigDecimal getSqmPriceSek() {
        return sqmPriceSek;
    }

    public void setSqmPriceSek(BigDecimal sqmPriceSek) {
        this.sqmPriceSek = sqmPriceSek;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        city = city;
    }

    public String getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }
}

