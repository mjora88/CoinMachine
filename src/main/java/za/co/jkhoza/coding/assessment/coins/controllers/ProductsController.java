package za.co.jkhoza.coding.assessment.coins.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import za.co.jkhoza.coding.assessment.coins.model.Product;
import za.co.jkhoza.coding.assessment.coins.model.PurchaseRequest;
import za.co.jkhoza.coding.assessment.coins.model.PurchaseResponse;

import javax.validation.Valid;
import java.util.Random;

@Controller
public class ProductsController extends AbstractController {


    @GetMapping("/manageProducts")
    public String getProductsPage(Model model) {
        model.addAttribute("currentProduct", new Product());
        return "products";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@Valid @ModelAttribute("currentProduct") Product currentProduct) {

        if (currentProduct.getProductCode() == null || currentProduct.getProductCode().trim().isEmpty()) {
            currentProduct.setProductCode(new Random().nextInt(1000) + "");
        }
        productService.saveProduct(currentProduct);

        return "redirect:/manageProducts";
    }

    @GetMapping("/editProduct/{id}")
    public String editProductById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("currentProduct", productService.findById(id));
        return "products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductById(@PathVariable("id") Long id, Model model) {

        productService.deleteProductbyId(id);
        return "redirect:/manageProducts";
    }


    @GetMapping("/buy/{id}")
    public String buyProductWithId(@PathVariable("id") Long id, Model model) {

        Product product = productService.findById(id);
        PurchaseRequest purchaseRequest = new PurchaseRequest();
        purchaseRequest.setProduct(product);
        model.addAttribute("purchaseRequest", purchaseRequest);

        return "buyProduct";
    }

    @PostMapping("/buyNow")
    public String buyNow(@Valid @ModelAttribute("purchaseRequest") PurchaseRequest purchaseRequest, Model model) {

        if (purchaseRequest.getAmountPaid() < purchaseRequest.getProduct().getPrice()) {
            model.addAttribute("purchaseMessage", "Add R" + (purchaseRequest.getProduct().getPrice() - purchaseRequest.getAmountPaid()));

            model.addAttribute("purchaseRequest", purchaseRequest);
            return "buyProduct";
        }

        PurchaseResponse purchaseResponse = productService.buy(purchaseRequest);
        model.addAttribute("purchaseResponse", purchaseResponse);
        return "buyProduct";
    }
}
