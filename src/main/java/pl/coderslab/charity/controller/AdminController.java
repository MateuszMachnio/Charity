package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.interfaces.InstitutionService;
import pl.coderslab.charity.service.interfaces.UserService;

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

}
