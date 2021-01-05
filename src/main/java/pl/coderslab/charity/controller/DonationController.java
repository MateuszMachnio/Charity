package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.interfaces.CategoryService;
import pl.coderslab.charity.service.interfaces.InstitutionService;

import java.util.Set;
import java.util.TreeSet;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private final InstitutionService institutionService;
    private final CategoryService categoryService;

    public DonationController(InstitutionService institutionService, CategoryService categoryService) {
        this.institutionService = institutionService;
        this.categoryService = categoryService;
    }

    @ModelAttribute("institutions")
    public Set<Institution> getInstitutions() {
        return new TreeSet<>(institutionService.findAllInstitutions());
    }

    @ModelAttribute("categories")
    public Set<Category> getCategories() {
        return new TreeSet<>(categoryService.findAllCategories());
    }

    @GetMapping("/new")
    public String newDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "newDonation";
    }

}
