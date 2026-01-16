package vaisselle.controllers.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vaisselle.models.tables.Cart;
import vaisselle.models.tables.CartDetail;
import vaisselle.models.tables.DiscountCart;
import vaisselle.models.tables.User;
import vaisselle.services.CartDetailService;
import vaisselle.services.CartService;
import vaisselle.services.CategoryService;
import vaisselle.services.ColorService;
import vaisselle.services.DiscountCartService;
import vaisselle.services.ProductService;
import vaisselle.services.SizeService;
import vaisselle.services.TypeService;
import vaisselle.services.UserService;
import vaisselle.services.VProductService;

@Controller
@RequestMapping("/client/cart")
public class CartController {

    private final UserService userService;
    private final VProductService vproductService;
    private final ProductService productService;
    private final CartService cartService;
    private final CartDetailService cartDetailService;
    private final DiscountCartService discountCartService;

    public CartController(UserService userService, VProductService vproductService,
            ProductService productService, CartService cartService, CartDetailService cartDetailService, DiscountCartService discountCartService) {
        this.userService = userService;
        this.vproductService = vproductService;
        this.productService = productService;
        this.cartService = cartService;
        this.cartDetailService = cartDetailService;
        this.discountCartService = discountCartService;
    }

    @GetMapping("")
    public String viewProducts(Model model, HttpSession session) {
        Long idCart = (Long) session.getAttribute("idCart");
        model.addAttribute("cart", cartService.makeCart(idCart));
        DiscountCart dc = discountCartService.getAllDiscountCart().get(0);
        model.addAttribute("param", dc);
        return "client/cart/index";
    }

     @GetMapping("/param")
    public String viewP(Model model, HttpSession session) {
        DiscountCart dc = discountCartService.getAllDiscountCart().get(0);
        model.addAttribute("paramm", dc);
        model.addAttribute("parammm", productService.getAllProducts().get(0));
        return "client/cart/param";
    }

    @PostMapping("")
    public String viewModel(@RequestParam("idProduct") Long idProduct,
            @RequestParam("qtt") Integer qtt, Model model,
            HttpSession session) {

        Long idCart = (Long) session.getAttribute("idCart");
        Long idUser = (Long) session.getAttribute("idUser");
        User u = userService.getUser(idUser);

        if (idCart == null) {
            Cart c = new Cart();
            c.setUser(u);
            c = cartService.save(c);
            CartDetail cd = new CartDetail();
            cd.setCart(c);
            cd.setQtt(qtt);
            cd.setProduct(productService.getProduct(idProduct));
            cartDetailService.save(cd);

            session.setAttribute("idCart", c.getId());
        } else {
            Cart c = cartService.findById(idCart);
            c = cartService.addDetails(c, idProduct, qtt);
        }
        return "redirect:/client/cart";
    }

}
