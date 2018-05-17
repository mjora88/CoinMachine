package za.co.jkhoza.coding.assessment.coins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.jkhoza.coding.assessment.coins.model.Product;
import za.co.jkhoza.coding.assessment.coins.model.PurchaseRequest;
import za.co.jkhoza.coding.assessment.coins.model.PurchaseResponse;
import za.co.jkhoza.coding.assessment.coins.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();

    }

    @Override
    public List<Product> getAllAvailableProduct() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getQuantity() > 0)
                .collect(Collectors.toList());

    }

    @Override
    public List<Product> findByGivenCat(Product.ProductCategory category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public void saveProduct(Product currentProduct) {
        productRepository.save(currentProduct);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public void deleteProductbyId(Long id) {
        productRepository.delete(id);
    }

    @Override
    public PurchaseResponse buy(PurchaseRequest purchaseRequest) {

        double productPrice = purchaseRequest.getProduct().getPrice();
        double amountPaid = purchaseRequest.getAmountPaid();
        double change = amountPaid - productPrice;

        PurchaseResponse purchaseResponse = new PurchaseResponse();
        purchaseResponse.setTotalChange(change);
        purchaseResponse.setProductName(purchaseRequest.getProduct().getName());

        double note200 = 200, note100 = 100, note50 = 50, note20 = 20, note10 = 10, coin5 = 5, coin2 = 2,
                coin1 = 1, coin50c = 0.5, coin20c = 0.2, coin10c = 0.1, coin5c = 0.05;

        int note200Counter = 0;
        int note100Counter = 0;
        int note50Counter = 0;
        int note20Counter = 0;
        int note10Counter = 0;
        int coin5Counter = 0;
        int coin2Counter = 0;
        int coin1Counter = 0;
        int coin50cCounter = 0;
        int coin20cCounter = 0;
        int coin10cCounter = 0;
        int coin5cCounter = 0;

        while (change >= note200) {
            note200Counter++;
            change -= note200;

        }
        if (note200Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(note200Counter + " x R" + note200);
        }
        while (change >= note100) {
            note100Counter++;
            change -= note100;
        }

        if (note100Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(note100Counter + " x R" + note100);
        }


        while (change >= note50) {
            note50Counter++;
            change -= note50;
        }
        if (note50Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(note50Counter + " x R" + note50);
        }


        while (change >= note20) {
            note20Counter++;
            change -= note20;
        }

        if (note20Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(note20Counter + " x R" + note20);
        }

        while (change >= note10) {
            note10Counter++;
            change -= note10;
        }
        if (note10Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(note10Counter + " x R" + note10);
        }

        while (change >= coin5) {
            coin5Counter++;
            change -= coin5;
        }

        if (coin5Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin5Counter + " x R" + coin5);
        }
        while (change >= coin2) {
            coin2Counter++;
            change -= coin2;
        }
        if (coin2Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin2Counter + " x R" + coin2);
        }

        while (change >= coin1) {
            coin1Counter++;
            change -= coin1;
        }
        if (coin1Counter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin1Counter + " x R" + coin1);
        }

        while (change >= coin50c) {
            coin50cCounter++;
            change -= coin50c;
        }

        if (coin50cCounter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin50cCounter + " x R" + coin50c + "c");
        }

        while (change >= coin20c) {
            coin20cCounter++;
            change -= coin20c;
        }

        if (coin20cCounter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin20cCounter + " x R" + coin20c + "c");
        }

        while (change >= coin10c) {
            coin10cCounter++;
            change -= coin10c;
        }
        if (coin10cCounter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin10cCounter + " x R" + coin10c + "c");
        }

        while (change >= coin5c) {
            coin5cCounter++;
            change -= coin5c;
        }
        if (coin5cCounter > 0) {
            purchaseResponse.getChangeBreakdown().add(coin5cCounter + " x R" + coin5c + "c");
        }

        return purchaseResponse;
    }


}
