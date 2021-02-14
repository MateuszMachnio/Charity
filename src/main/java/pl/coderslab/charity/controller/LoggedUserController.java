package pl.coderslab.charity.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.service.interfaces.UserService;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping("logged-user")
public class LoggedUserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public LoggedUserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("user")
    public String userName() {
        return userService.getCurrentUser().getFirstName();
    }

    @ModelAttribute("role")
    public String roleName() {
        return userService.getCurrentUser().getRole();
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "loggedUser/dashboard";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(Model model) {
        model.addAttribute("loggedInUser", userService.getCurrentUser().getFirstName());
        return "loggedUser/accessDenied";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("appUser", userService.getCurrentUser());
        return "loggedUser/profile";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        AppUser currentUser = userService.getCurrentUser();
        currentUser.setPassword("");
        model.addAttribute("appUser", currentUser);
        return "loggedUser/edit";
    }

    @PostMapping("/editing")
    public String editing(@Valid AppUser appUser, BindingResult result) {
        AdminController.checkIfUserEditingHasErrors(appUser, result, userService, passwordEncoder);
        if (result.hasErrors()) {
            return "loggedUser/edit";
        }
        userService.updateUser(appUser);
        AppUser byId = userService.findById(appUser.getId());
        Collection<SimpleGrantedAuthority> nowAuthorities =(Collection<SimpleGrantedAuthority>)SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(byId.getEmail(), byId.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "loggedUser/userEditingConfirmation";
    }

}
