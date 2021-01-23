package pl.coderslab.charity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.AppUser;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.interfaces.InstitutionService;
import pl.coderslab.charity.service.interfaces.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final InstitutionService institutionService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AdminController(InstitutionService institutionService, UserService userService, PasswordEncoder passwordEncoder) {
        this.institutionService = institutionService;
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

    @PostMapping("/institution/delete")
    public String deleteInstitution(@ModelAttribute("institutionId") long institutionId, Model model) {
        model.addAttribute("institution", institutionService.findById(institutionId));
        return "admin/confirmInstitutionDeleting";
    }

    @PostMapping("/institution/deleting")
    public String deletingInstitution(Institution institution) {
        institutionService.deleteInstitution(institution.getId());
        return "redirect:/admin/institutions";
    }

    @GetMapping("/admins")
    public String adminsList(Model model) {
        model.addAttribute("adminEmail", userService.getPrincipal());
        model.addAttribute("admins", userService.findAllByRoleEquals("ADMIN"));
        return "admin/admins";
    }

    @GetMapping("/add")
    public String addAdmin(Model model) {
        model.addAttribute("appUser", new AppUser());
        return "admin/newAdmin";
    }

    @PostMapping("/add")
    public String addingAdmin(@Valid AppUser appUser, BindingResult result) {
        if (checkIfAddingUserHasErrors(appUser, result, userService)) return "admin/newAdmin";
        userService.saveAdmin(appUser);
        return "redirect:admins";
    }

    @PostMapping("/edit")
    public String editAdmin(long adminId, Model model) {
        AppUser appUser = userService.findById(adminId);
        appUser.setPassword("");
        model.addAttribute("appUser", appUser);
        return "admin/edit";
    }

    @PostMapping("/editing")
    public String editingAdmin(@Valid AppUser appUser, BindingResult result) {
        checkIfUserEditingHasErrors(appUser, result);
        if (result.hasErrors()) {
            return "admin/edit";
        }
        userService.updateUser(appUser);
        return "redirect:admins";
    }

    private void checkIfUserEditingHasErrors(@Valid AppUser appUser, BindingResult result) {
        AppUser byId = userService.findById(appUser.getId());
        if (!passwordEncoder.matches(appUser.getOldPassword(), byId.getPassword())) {
            result.rejectValue("oldPassword", "non.valid.password");
        }
        if(userService.existsByEmail(appUser.getEmail()) && !byId.getEmail().equals(appUser.getEmail())){
            result.rejectValue("email", "non.unique.email");
        }
        if (appUser.getPassword() == null || !appUser.getPassword().equals(appUser.getRepeatPassword())) {
            result.rejectValue("password", "non.identical.passwords");
        }
    }

    static boolean checkIfAddingUserHasErrors(@Valid AppUser appUser, BindingResult result, UserService userService) {
        if(userService.existsByEmail(appUser.getEmail())){
            result.rejectValue("email", "non.unique.email");
        }
        if (appUser.getPassword() == null || !appUser.getPassword().equals(appUser.getRepeatPassword())) {
            result.rejectValue("password", "non.identical.passwords");
        }
        return result.hasErrors();
    }

    @PostMapping("/delete")
    public String deleteAdmin(long adminId, Model model) {
        AppUser appUser = userService.findById(adminId);
        if (userService.getCurrentUser().equals(appUser)) {
            return "admin/deleteError";
        }
        model.addAttribute("appUser", appUser);
        return "admin/delete";
    }

    @PostMapping("/deleting")
    public String deletingAdmin(long adminId, Model model) {
        userService.deleteUser(adminId);
        return "redirect:admins";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        model.addAttribute("users", userService.findAllByRoleEquals("USER"));
        model.addAttribute("blocked", userService.findAllByRoleEquals("BLOCKED"));
        return "admin/users";
    }

    @PostMapping("/user/edit")
    public String editUser(long userId, Model model) {
        AppUser appUser = userService.findById(userId);
        appUser.setPassword("");
        model.addAttribute("appUser", appUser);
        return "admin/userEdit";
    }

    @PostMapping("/user/editing")
    public String editingUser(@Valid AppUser appUser, BindingResult result) {
        checkIfUserEditingHasErrors(appUser, result);
        if (result.hasErrors()) {
            return "admin/userEdit";
        }
        userService.updateUser(appUser);
        return "redirect:/admin/users";
    }

    @PostMapping("/user/block")
    public String blockUser(long userId) {
        AppUser appUser = userService.findById(userId);
        appUser.setRole("BLOCKED");
        userService.updateUser(appUser);
        return "redirect:/admin/users";
    }


}
