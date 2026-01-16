package vaisselle.controllers.admin;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vaisselle.models.tables.User;
import vaisselle.services.FileService;
import vaisselle.services.UserService;

@Controller("UserBackController")
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;

    public UserController() {
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
    public String saveUser(@RequestParam String name,
            @RequestParam("photoFile") MultipartFile photoFile) throws IOException {

        User user = new User();
        user.setName(name);

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
    public String updateUser(@RequestParam("id") Long id, @RequestParam("name") String name,
            @RequestParam("photoFile") MultipartFile photoFile) throws IOException {

        User user = userService.getUser(id);
        user.setName(name);

        String img = fileService.savePhoto(photoFile);
        if (!img.isEmpty()) {
            user.setImg(img);
        }

        userService.updateUser(user);
        return "redirect:/admin/users/" + user.getId();
    }

    @GetMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }
}
