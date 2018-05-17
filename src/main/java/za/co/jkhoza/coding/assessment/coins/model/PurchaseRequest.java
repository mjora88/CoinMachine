package za.co.jkhoza.coding.assessment.coins.model;

public class PurchaseRequest {

    private double amountPaid;
    private Product product;

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
