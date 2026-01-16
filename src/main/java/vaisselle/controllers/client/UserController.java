package vaisselle.controllers.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vaisselle.models.tables.User;
import vaisselle.services.UserService;

@Controller("UserFrontController")
@RequestMapping("/client/auth")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
    }

    @GetMapping("/login")
    public String loginForm(Model model) {

        return "client/auth/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user,  @RequestParam("name") String name, Model model, HttpSession session) {
        User test = new User();
        test.setName(name);
        User u = userService.login(test);
        if (u == null) {
            model.addAttribute("error", "login failed");
            return "client/auth/login";
        }
        session.setAttribute("idUser", u.getId());
        return "redirect:/client/";
    }

    @GetMapping("/logout")
    public String logout(@ModelAttribute User user, Model model, HttpSession session) {
        session.invalidate();;
        return "redirect:/client/";
    }

    @GetMapping("")
    public String index() {
        return "redirect:/client/auth/login";
    }

    @GetMapping("/")
    public String indexx() {
        return "redirect:/client/auth/login";
    }
}
