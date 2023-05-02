package com.example.practice_mobilelele.web;

import com.example.practice_mobilelele.model.binding.OfferAddBindingModel;
import com.example.practice_mobilelele.model.binding.UpdateOfferBindingModel;
import com.example.practice_mobilelele.model.enums.EngineEnum;
import com.example.practice_mobilelele.model.enums.TransmissionEnum;
import com.example.practice_mobilelele.model.service.OfferAddServiceModel;
import com.example.practice_mobilelele.model.service.UpdateOfferServiceModel;
import com.example.practice_mobilelele.model.view.OfferDetailsViewModel;
import com.example.practice_mobilelele.service.BrandService;
import com.example.practice_mobilelele.service.Impl.ApplicationUser;
import com.example.practice_mobilelele.service.OfferService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;
    private final ModelMapper modelMapper;

    public OfferController(OfferService offerService, BrandService brandService, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.brandService = brandService;
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

    @PreAuthorize("@offerServiceImpl.isOwner(#principal.name, #id)")
    @DeleteMapping("/offers/{id}/delete")
    public String deleteOffer(@PathVariable Long id, Principal principal) {

        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    @GetMapping("/offers/add")
    public String addOffer(Model model) {

        if (!model.containsAttribute("offerAddBindingModel")) {

            model.addAttribute("offerAddBindingModel", new OfferAddBindingModel());
            model.addAttribute("engines", EngineEnum.values());
            model.addAttribute("brandsModels", brandService.getAllBrands());
            model.addAttribute("transmissions", TransmissionEnum.values());
        }

        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOfferConfirm(@Valid OfferAddBindingModel offerAddBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                  @AuthenticationPrincipal ApplicationUser userPrincipal) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerAddBindingModel", offerAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindingModel", bindingResult);
            redirectAttributes.addFlashAttribute("engines", EngineEnum.values());
            redirectAttributes.addFlashAttribute("brandsModels", brandService.getAllBrands());
            redirectAttributes.addFlashAttribute("transmissions", TransmissionEnum.values());

            return "redirect:/offers/add";
        }

        OfferAddServiceModel offerAddServiceModel =
                offerService.addOffer(modelMapper.
                        map(offerAddBindingModel, OfferAddServiceModel.class), userPrincipal.getId());

        return "redirect:/offers/" + offerAddServiceModel.getId() + "/details";
    }
}
