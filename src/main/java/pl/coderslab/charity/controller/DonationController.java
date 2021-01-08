package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.interfaces.CategoryService;
import pl.coderslab.charity.service.interfaces.DonationService;
import pl.coderslab.charity.service.interfaces.InstitutionService;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final InstitutionService institutionService;
    private final CategoryService categoryService;
    private final DonationService donationService;

    public DonationController(InstitutionService institutionService, CategoryService categoryService, DonationService donationService) {
        this.institutionService = institutionService;
        this.categoryService = categoryService;
        this.donationService = donationService;
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions() {
        return institutionService.findAllInstitutions();
    }

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/new")
    public String newDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "newDonation";
    }

    @PostMapping("/new")
    public String saveDonation(@Valid Donation donation, BindingResult result) {
        if (result.hasErrors()) {
            return "newDonation";
        }
        donationService.saveDonation(donation);
        return "confirmation";
    }

}
