package pl.coderslab.charity.controller;

import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.interfaces.DonationService;
import pl.coderslab.charity.service.interfaces.InstitutionService;
import pl.coderslab.charity.service.interfaces.UserService;

import javax.validation.Valid;
import java.util.Locale;


@Controller
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final UserService userService;
    private final MessageSource messageSource;
    private final AuthenticationTrustResolver authenticationTrustResolver;

    public HomeController(InstitutionService institutionService, DonationService donationService, UserService userService, MessageSource messageSource, AuthenticationTrustResolver authenticationTrustResolver) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.userService = userService;
        this.messageSource = messageSource;
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
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String saveUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        if(userService.findByEmail(user.getEmail()) != null){
            FieldError error = new FieldError("user","email", messageSource.getMessage("non.unique.email", new String[]{user.getEmail()}, Locale.getDefault()));
            result.addError(error);
            return "registration";
        }
        userService.saveUser(user);
        model.addAttribute("loggedInUser", user.getFirstName());
        return "registrationSuccess";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(Model model) {
        model.addAttribute("loggedInUser", userService.getCurrentUser().getFirstName());
        return "accessDenied";
    }

    @GetMapping("/login")
    public String login() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/logged-user/dashboard";
        }
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }

}
