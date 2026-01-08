package vaisselle.controllers.admin;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/session")
public class SessionController {

    public SessionController() {
    }

    @GetMapping("")
    public String viewSession(
            @RequestParam(value = "show", required = false) String show,
            Model model, HttpSession session) {

        Object ses = session.getAttribute(show);

        String value = "session name";
        if (ses == null) {
            value = "session not found";
        } else {
            value = (String) ses;
        }

        model.addAttribute("show", show);
        model.addAttribute("value", value);
        return "admin/test/session";

    }

    @PostMapping("")
    public String showSession(
            @RequestParam("name") String name,
            @RequestParam("value") String value,
            HttpSession session) {

        session.setAttribute(name, value);
        return "redirect:/admin/session?show=" + name;

    }
}
