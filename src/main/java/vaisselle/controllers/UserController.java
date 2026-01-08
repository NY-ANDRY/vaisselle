package vaisselle.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.services.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") Long id,Model model) {
        var user = userService.getUser(id);
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping("/create")
    public String createUserForm() {
        return "user-create";
    }
}
