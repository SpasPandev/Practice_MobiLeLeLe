package com.example.practice_mobilelele.service;

import com.example.practice_mobilelele.model.binding.OfferAddBindingModel;
import com.example.practice_mobilelele.model.entity.Offer;
import com.example.practice_mobilelele.model.service.OfferAddServiceModel;
import com.example.practice_mobilelele.model.service.UpdateOfferServiceModel;
import com.example.practice_mobilelele.model.view.OfferDetailsViewModel;

import java.util.List;

public interface OfferService {

    List<Offer> getAllOffers();

    OfferDetailsViewModel findById(Long id, String currentUser);

    void updateOffer(UpdateOfferServiceModel updateOfferServiceModel);

    void deleteOffer(Long id);

    OfferAddServiceModel addOffer(OfferAddServiceModel offerAddServiceModel, Long ownerId);

    boolean isOwner(String username, Long offerId);
}
