package com.example.practice_mobilelele.service.Impl;

import com.example.practice_mobilelele.model.entity.Offer;
import com.example.practice_mobilelele.repository.OfferRepository;
import com.example.practice_mobilelele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public List<Offer> getAllOffers() {

        return offerRepository.findAll();
    }
}
