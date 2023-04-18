package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.binding.OfferAddBindingModel;
import com.example.practice_mobilelele.model.entity.Offer;
import com.example.practice_mobilelele.model.service.OfferAddServiceModel;
import com.example.practice_mobilelele.model.service.UpdateOfferServiceModel;
import com.example.practice_mobilelele.model.view.OfferDetailsViewModel;
import com.example.practice_mobilelele.repository.ModelRepository;
import com.example.practice_mobilelele.repository.OfferRepository;
import com.example.practice_mobilelele.repository.UserRepository;
import com.example.practice_mobilelele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelRepository modelRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.userRepository = userRepository;
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

    @Override
    public OfferAddServiceModel addOffer(OfferAddServiceModel offerAddServiceModel, Long ownerId) {

        Offer newOffer = new Offer();
        newOffer.setDescription(offerAddServiceModel.getDescription());
        newOffer.setEngine(offerAddServiceModel.getEngine());
        newOffer.setImageUrl(offerAddServiceModel.getImageUrl());
        newOffer.setMileage(offerAddServiceModel.getMileage());
        newOffer.setPrice(offerAddServiceModel.getPrice());
        newOffer.setTransmission(offerAddServiceModel.getTransmission());
        newOffer.setYear(offerAddServiceModel.getYear());
        newOffer.setCreated(LocalDate.now());
        newOffer.setModified(LocalDate.now());
        newOffer.setModel(modelRepository.findById(offerAddServiceModel.getModelId()).get());
        newOffer.setSeller(userRepository.findById(ownerId).orElseThrow());

        return modelMapper.map(offerRepository.save(newOffer), OfferAddServiceModel.class);
    }
}
