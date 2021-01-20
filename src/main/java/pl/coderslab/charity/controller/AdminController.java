package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.interfaces.InstitutionService;
import pl.coderslab.charity.service.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;
    private final UserService userService;

    public AdminController(InstitutionService institutionService, UserService userService) {
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @ModelAttribute("user")
    public String userName() {
        return userService.getCurrentUser().getFirstName();
    }

    @ModelAttribute("role")
    public String roleName() {
        return userService.getCurrentUser().getRole();
    }

    @GetMapping("/institutions")
    public String institutions(Model model) {
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        return "admin/institutions";
    }

    @GetMapping("/institution/add")
    public String addInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/addInstitution";
    }

    @PostMapping("/institution/add")
    public String addingInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/addInstitution";
        }
        institutionService.saveInstitution(institution);
        return "redirect:/admin/institutions";
    }

    @PostMapping("/institution/edit")
    public String editInstitution(@ModelAttribute("institutionId") long institutionId, Model model) {
        model.addAttribute("institution", institutionService.findById(institutionId));
        return "admin/editInstitution";
    }

    @PostMapping("/institution/editing")
    public String editingInstitution(@Valid Institution institution, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/editInstitution";
        }
        institutionService.updateInstitution(institution);
        return "redirect:/admin/institutions";
    }

}
