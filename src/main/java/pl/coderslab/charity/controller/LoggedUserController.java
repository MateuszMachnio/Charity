package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
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

    @GetMapping("/dashboard")
    public String dashboard() {
        return "loggedUser/dashboard";
    }
}
