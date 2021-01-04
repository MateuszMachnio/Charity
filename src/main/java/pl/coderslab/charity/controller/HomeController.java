package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.interfaces.DonationService;
import pl.coderslab.charity.service.interfaces.InstitutionService;

import java.util.TreeSet;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", new TreeSet<>(institutionService.findAllInstitutions()));
        model.addAttribute("donationsQuantity", donationService.quantityOfDonations());
        return "index";
    }
}
