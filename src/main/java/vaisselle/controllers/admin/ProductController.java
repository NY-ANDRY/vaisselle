package vaisselle.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vaisselle.models.tables.Product;
import vaisselle.models.tables.ProductImage;
import vaisselle.services.ColorService;
import vaisselle.services.FileService;
import vaisselle.services.ModelService;
import vaisselle.services.ProductService;
import vaisselle.services.SizeService;
import vaisselle.repositories.ProductImageRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller("AdminProductsController")
@RequestMapping("/admin/products")
public class ProductController {

    private final ProductService productService;
    private final ModelService modelService;
    private final SizeService sizeService;
    private final ColorService colorService;
    private final FileService fileService;
    private final ProductImageRepository productImageRepository;

    public ProductController(ProductService productService, ModelService modelService,
            SizeService sizeService, ColorService colorService, FileService fileService,
            ProductImageRepository productImageRepository) {
        this.productService = productService;
        this.modelService = modelService;
        this.sizeService = sizeService;
        this.colorService = colorService;
        this.fileService = fileService;
        this.productImageRepository = productImageRepository;
    }

    @GetMapping("")
    public String index(Model model, @RequestParam(required = false) Long editId) {
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("models", modelService.getAllModels());
        model.addAttribute("sizes", sizeService.getAllSizes());
        model.addAttribute("colors", colorService.getAllColors());

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

    @PostMapping("")
    public String store(@ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Product savedProduct = productService.saveProduct(product);

        String imgUrl = fileService.savePhoto(imageFile);
        if (!imgUrl.isEmpty()) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(savedProduct);
            productImage.setUrl(imgUrl);
            productImage.setDefault(true);
            productImageRepository.save(productImage);
        }

        return "redirect:/admin/products?editId=" + savedProduct.getId();
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id) {
        return "redirect:/admin/products?editId=" + id;
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Product product,
            @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        productService.updateProduct(product);

        String imgUrl = fileService.savePhoto(imageFile);
        if (!imgUrl.isEmpty()) {
            ProductImage productImage = new ProductImage();
            productImage.setProduct(product);
            productImage.setUrl(imgUrl);
            productImage.setDefault(true);
            productImageRepository.save(productImage);
        }

        return "redirect:/admin/products?editId=" + product.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/images/{id}/delete")
    public String deleteImage(@PathVariable Long id) {
        ProductImage image = productImageRepository.findById(id).orElse(null);
        if (image != null) {
            Long productId = image.getProduct().getId();
            fileService.deleteFile(image.getUrl());
            productImageRepository.delete(image);
            return "redirect:/admin/products?editId=" + productId;
        }
        return "redirect:/admin/products";
    }
}
