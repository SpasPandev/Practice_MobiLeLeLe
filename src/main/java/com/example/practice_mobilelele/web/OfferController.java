package com.example.practice_mobilelele.web;

import com.example.practice_mobilelele.model.binding.UpdateOfferBindingModel;
import com.example.practice_mobilelele.model.enums.EngineEnum;
import com.example.practice_mobilelele.model.enums.TransmissionEnum;
import com.example.practice_mobilelele.model.service.UpdateOfferServiceModel;
import com.example.practice_mobilelele.model.view.OfferDetailsViewModel;
import com.example.practice_mobilelele.service.OfferService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
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

    @GetMapping("/offers/{id}/update")
    public String updateOffer(@PathVariable Long id, Model model) {

        OfferDetailsViewModel offerDetailsViewModel = offerService.findById(id);
        UpdateOfferBindingModel updateOfferBindingModel = modelMapper.map(offerDetailsViewModel, UpdateOfferBindingModel.class);

        model.addAttribute("updateOfferBindingModel", updateOfferBindingModel);
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }

    @PatchMapping("/offers/{id}/update")
    public String updateOfferConfirm(@PathVariable Long id, @Valid UpdateOfferBindingModel updateOfferBindingModel,
                                     BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("updateOfferBindingModel", updateOfferBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateOfferBindingModel", bindingResult);

            return "redirect:/offers/" + id + "/update/errors";
        }

        offerService.updateOffer(modelMapper.map(updateOfferBindingModel, UpdateOfferServiceModel.class));

        return "redirect:/offers/" + id + "/details";
    }

    @GetMapping("/offers/{id}/update/errors")
    public String updateOfferErrors(@PathVariable Long id, Model model) {

        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());

        return "update";
    }
}
