package za.co.jkhoza.coding.assessment.coins.service;

import za.co.jkhoza.coding.assessment.coins.model.Product;
import za.co.jkhoza.coding.assessment.coins.model.PurchaseRequest;
import za.co.jkhoza.coding.assessment.coins.model.PurchaseResponse;

import java.util.List;

public interface ProductService {
    List<Product> getAllProduct();

    List<Product> getAllAvailableProduct();

    public List<Product> findByGivenCat(Product.ProductCategory category);

    void saveProduct(Product currentProduct);

    Product findById(Long id);

    void deleteProductbyId(Long id);

    PurchaseResponse buy(PurchaseRequest purchaseRequest);
}
