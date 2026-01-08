package vaisselle.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientController {

    public ClientController() {
    }

    @GetMapping("")
    public String home() {
        return "client/home";
    }
}
