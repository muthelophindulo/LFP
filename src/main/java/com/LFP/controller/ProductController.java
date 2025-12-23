package com.LFP.controller;

import com.LFP.model.customer;
import com.LFP.model.order;
import com.LFP.model.product;
import com.LFP.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/new")
    public String showForm(Model model){
        model.addAttribute("product",new product());
        return "products/form";
    }

    @PostMapping("/save")
    public String save(
            @ModelAttribute("product") product product,
            RedirectAttributes redirectAttributes
    ){
        product x = product;
        if (x.getProductID() == null || x.getProductID().isEmpty()) {

            x.setProductID("PROD-" + System.currentTimeMillis());
        }
        productService.save(x);

        return "redirect:/dashboard";
    }

    @GetMapping("/list")
    public String showProducts(Model model){
        List<product> products = productService.getProducts();
        model.addAttribute("products", products);

        // Add statistics
        model.addAttribute("totalProducts", productService.getProducts().size());

        return "products/products";

    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        productService.delete(id);

        return "redirect:/products/list";
    }

    @GetMapping("/edit/{id}")
    public String update(@PathVariable Long id, Model model){
        product updatedproduct = productService.getProdById(id);

        model.addAttribute("product", updatedproduct);

        return "products/update";
    }

    @PostMapping("/update")
    public String saveUpdate(
            @ModelAttribute product product
    ){
        product.setProductID( productService.getProdById(product.getId()).getProductID() );
        productService.save(product);
        return "redirect:/products/list";
    }

    @GetMapping("/view/{id}")
    public String View(@PathVariable Long id,Model model){
        product x = productService.getProdById(id);

        model.addAttribute("product",x);

        return "products/view";
    }
}
