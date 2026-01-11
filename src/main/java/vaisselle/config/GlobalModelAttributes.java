package vaisselle.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vaisselle.models.tables.User;
import vaisselle.services.UserService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {

    private final UserService userService;

    public GlobalModelAttributes(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addGlobalAttributes(Model model, HttpServletRequest request, HttpSession session) {
        Long idUser = (Long) session.getAttribute("idUser");
        User u = null;

        if (idUser != null) {
            u = userService.getUser(idUser);
        }

        model.addAttribute("user", u);
        model.addAttribute("uri", request.getRequestURI());
    }
}
