package pl.coderslab.charity.controller;

import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.service.interfaces.DonationService;
import pl.coderslab.charity.service.interfaces.InstitutionService;
import pl.coderslab.charity.service.interfaces.UserService;

import javax.validation.Valid;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final AuthenticationTrustResolver authenticationTrustResolver;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService, AuthenticationTrustResolver authenticationTrustResolver) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.authenticationTrustResolver = authenticationTrustResolver;
    }

    @GetMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.findAllInstitutions());
        model.addAttribute("donationsQuantity", donationService.quantityOfDonations());
        model.addAttribute("sumOfBags", donationService.sumOfBags());
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "registration";
    }

    @PostMapping("/register")
    public String saveUser(@Valid AppUser appUser, BindingResult result, Model model) {
        if (AdminController.checkIfAddingUserHasErrors(appUser, result, userService)) return "registration";
        userService.saveUser(appUser);
        model.addAttribute("loggedInUser", appUser.getFirstName());
        return "registrationSuccess";
    }

    @GetMapping("/login")
    public String login() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/logged-user/dashboard";
        }
    }

    @GetMapping("/logout")
    public String logoutPage (){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return "redirect:/login?logout";
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
