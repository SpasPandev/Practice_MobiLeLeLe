package com.example.practice_mobilelele.model.view;

import com.example.practice_mobilelele.model.entity.Model;
import com.example.practice_mobilelele.model.entity.User;
import com.example.practice_mobilelele.model.enums.EngineEnum;
import com.example.practice_mobilelele.model.enums.TransmissionEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OfferDetailsViewModel {

    private Long id;
    private String description;
    private EngineEnum engine;
    private String imageUrl;
    private Integer mileage;
    private BigDecimal price;
    private TransmissionEnum transmission;
    private Integer year;
    private LocalDate created;
    private LocalDate modified;
    private Model model;
    private User seller;
    private boolean canDelete;

    public OfferDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public OfferDetailsViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
