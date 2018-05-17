package za.co.jkhoza.coding.assessment.coins.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import za.co.jkhoza.coding.assessment.coins.model.Product;
import za.co.jkhoza.coding.assessment.coins.model.User;
import za.co.jkhoza.coding.assessment.coins.service.ProductService;
import za.co.jkhoza.coding.assessment.coins.service.UserService;

import java.util.List;

public class AbstractController {

    @Autowired
    protected ProductService productService;

    @Autowired
    protected UserService userService;

    @ModelAttribute(name = "allAvailableProduct")
    public List<Product> getAllAvailableProduct() {
        return productService.getAllAvailableProduct();
    }


    @ModelAttribute("userFullNames")
    public String getUserFullNames() {

        User currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getFirstName() + " " + currentUser.getLastName();
        }
        return "";
    }

    protected User getCurrentUser() {
        return userService.findByEmail(getCurrentUserName());
    }

    protected String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    @ModelAttribute(name = "allProducts")
    public List<Product> getAllProds() {
        return productService.getAllProduct();
    }

    @ModelAttribute(name = "allCategories")
    public Product.ProductCategory[] getAllCategories() {
        return Product.ProductCategory.values();
    }

    @ExceptionHandler(Exception.class)
    public String genericHandler(Exception e) {
        e.printStackTrace();
        return "redirect:/?error";
    }
}
