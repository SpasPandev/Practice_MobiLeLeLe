package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.entity.Offer;
import com.example.practice_mobilelele.model.service.UpdateOfferServiceModel;
import com.example.practice_mobilelele.model.view.OfferDetailsViewModel;
import com.example.practice_mobilelele.repository.OfferRepository;
import com.example.practice_mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Offer> getAllOffers() {

        return offerRepository.findAll();
    }

    @Override
    public OfferDetailsViewModel findById(Long id) {

        return modelMapper.map(offerRepository.findById(id).orElse(null), OfferDetailsViewModel.class);
    }

    @Override
    public void updateOffer(UpdateOfferServiceModel updateOfferServiceModel) {

        Offer offer = offerRepository.findById(updateOfferServiceModel.getId()).orElse(null);

        offer.setMileage(updateOfferServiceModel.getMileage());
        offer.setPrice(updateOfferServiceModel.getPrice());
        offer.setEngine(updateOfferServiceModel.getEngine());
        offer.setTransmission(updateOfferServiceModel.getTransmission());
        offer.setYear(updateOfferServiceModel.getYear());
        offer.setDescription(updateOfferServiceModel.getDescription());
        offer.setImageUrl(updateOfferServiceModel.getImageUrl());
        offer.setModified(LocalDate.now());

        offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(Long id) {

        offerRepository.deleteById(id);
    }
}
