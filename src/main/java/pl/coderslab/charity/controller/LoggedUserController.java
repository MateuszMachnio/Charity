package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.interfaces.UserService;

@Controller
@RequestMapping("logged-user")
public class LoggedUserController {

    private final UserService userService;

    public LoggedUserController(UserService userService) {
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

}
