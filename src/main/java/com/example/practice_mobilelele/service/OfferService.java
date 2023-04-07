package com.example.practice_mobilelele.service;

import com.example.practice_mobilelele.model.entity.Offer;
import com.example.practice_mobilelele.model.service.UpdateOfferServiceModel;
import com.example.practice_mobilelele.model.view.OfferDetailsViewModel;

import java.util.List;

public interface OfferService {

    List<Offer> getAllOffers();

    OfferDetailsViewModel findById(Long id);

    void updateOffer(UpdateOfferServiceModel updateOfferServiceModel);
}
