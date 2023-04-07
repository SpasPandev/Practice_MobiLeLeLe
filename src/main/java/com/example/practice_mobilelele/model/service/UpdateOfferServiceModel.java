package com.example.practice_mobilelele.model.service;

import com.example.practice_mobilelele.model.enums.EngineEnum;
import com.example.practice_mobilelele.model.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UpdateOfferServiceModel {

    private Long id;
    private Integer mileage;
    private BigDecimal price;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private Integer year;
    private String description;
    private String imageUrl;
    private LocalDate modified;

    public UpdateOfferServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }
}
