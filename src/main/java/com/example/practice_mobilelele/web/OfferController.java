package com.example.practice_mobilelele.web;

import com.example.practice_mobilelele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/offers/all")
    public String allOffers(Model model) {

        model.addAttribute("allOffers", offerService.getAllOffers());

        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String offerDetails(@PathVariable Long id, Model model) {

        model.addAttribute("offer", offerService.findById(id));

        return "details";
    }
}
