package vaisselle.controllers.admin;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vaisselle.models.tables.User;
import vaisselle.services.FileService;
import vaisselle.services.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

    private final UserService userService;
    private final FileService fileService;

    public UserController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("")
    public String viewUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users/users";
    }

    @GetMapping("/{id}")
    public String viewUser(@PathVariable("id") Long id, Model model) {
        var user = userService.getUser(id);
        model.addAttribute("user", user);
        return "admin/users/user";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("actionUrl", "/admin/users/");
        return "admin/users/form";
    }

    @PostMapping("/")
    public String saveUser(@ModelAttribute User user,
            @RequestParam("photoFile") MultipartFile photoFile) throws IOException {

        String img = fileService.savePhoto(photoFile);

        if (!img.isEmpty()) {
            user.setImg(img);
        }

        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/{id}/update")
    public String updateUserForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("actionUrl", "/admin/users/update");
        return "admin/users/form";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute User user,
            @RequestParam("photoFile") MultipartFile photoFile) throws IOException {

        if (user.getId() == null) {
            System.out.println(user.toString());
            return "redirect:/admin/users";
        }

        String img = fileService.savePhoto(photoFile);
        if (!img.isEmpty()) {
            user.setImg(img);
        }

        userService.updateUser(user);
        return "redirect:/admin/users/" + user.getId();
    }
}
