package vaisselle.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.models.tables.Product;
import vaisselle.services.ColorService;
import vaisselle.services.FileService;
import vaisselle.services.ModelService;
import vaisselle.services.ProductService;
import vaisselle.services.ProductImageService;
import vaisselle.services.SizeService;
import vaisselle.models.tables.ProductImage;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller("AdminProductsController")
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final ProductImageService productImageService;
    private final ModelService modelService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final FileService fileService;

    public ProductController(ProductService productService, ProductImageService productImageService,
            ModelService modelService, SizeService sizeService, ColorService colorService, FileService fileService) {
        this.productService = productService;
        this.productImageService = productImageService;
        this.modelService = modelService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.fileService = fileService;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(required = false) Long editId) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("product", new Product());
        if (editId != null) {
            model.addAttribute("editProduct", productService.getProduct(editId));
        }

        return "admin/products/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());
        return "admin/products/create";
    }

    @PostMapping(value = "", consumes = "multipart/form-data")
    public String store(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double location,
            @RequestParam Long model,
            @RequestParam Long size,
            @RequestParam Long color,
            @RequestParam(required = false) MultipartFile imageFile,
            @RequestParam(required = false) String redirectUrl) throws IOException {

        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setLocation(location);
        p.setModel(modelService.getModel(model));
        p.setSize(sizeService.getSize(size));
        p.setColor(colorService.getColor(color));

        Product savedProduct = productService.saveProduct(p);

        String imgUrl = fileService.savePhoto(imageFile);
        if (imgUrl != null && !imgUrl.isEmpty()) {
            ProductImage pi = new ProductImage();
            pi.setProduct(savedProduct);
            pi.setUrl(imgUrl);
            productImageService.saveProductImage(pi);
        }
        if (redirectUrl != null && !redirectUrl.isEmpty()) {
            return "redirect:" + redirectUrl;
        }
        return "redirect:/admin/products?editId=" + savedProduct.getId();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) {
        Product product = productService.getProduct(id);
        model.addAttribute("product", product);
        model.addAttribute("selectedModel", product.getModel());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());
        model.addAttribute("redirectUrl", "/admin/products/" + id);
        return "admin/products/show";
    }

    @PostMapping("/{productId}/images")
    public String addVariantImage(@PathVariable Long productId, @RequestParam("imageFile") MultipartFile imageFile)
            throws IOException {
        String imgUrl = fileService.savePhoto(imageFile);
        if (imgUrl != null && !imgUrl.isEmpty()) {
            Product p = productService.getProduct(productId);
            ProductImage pi = new ProductImage();
            pi.setProduct(p);
            pi.setUrl(imgUrl);
            productImageService.saveProductImage(pi);
        }
        return "redirect:/admin/products/" + productId;
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id) {
        return "redirect:/admin/products?editId=" + id;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product,
            @RequestParam(required = false) String redirectUrl) throws IOException {
        productService.updateProduct(product);

        if (redirectUrl != null && !redirectUrl.isEmpty()) {
            return "redirect:" + redirectUrl;
        }

        return "redirect:/admin/products?editId=" + product.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, @RequestParam(required = false) String redirectUrl) {
        Product p = productService.getProduct(id);
        Long idModel = p.getModel().getId();
        productService.deleteProduct(id);
        if (redirectUrl != null && !redirectUrl.isEmpty()) {
            return "redirect:" + redirectUrl;
        }
        return "redirect:/admin/models/" + idModel;
    }

    // @PostMapping("/images/{id}/delete")
    // public String deleteImage(@PathVariable Long id) {
    // ProductImage image = productImageRepository.findById(id).orElse(null);
    // if (image != null) {
    // Long productId = image.getProduct().getId();
    // fileService.deleteFile(image.getUrl());
    // productImageRepository.delete(image);
    // return "redirect:/admin/products?editId=" + productId;
    // }
    // return "redirect:/admin/products";
    // }
}
