package za.co.jkhoza.coding.assessment.coins.model;

import java.util.ArrayList;
import java.util.List;

public class PurchaseResponse {

    private double totalChange;
    private String productName;
    private List<String> changeBreakdown = new ArrayList<>();

    public double getTotalChange() {
        return totalChange;
    }

    public void setTotalChange(double totalChange) {
        this.totalChange = totalChange;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public List<String> getChangeBreakdown() {
        return changeBreakdown;
    }

    public void setChangeBreakdown(List<String> changeBreakdown) {
        this.changeBreakdown = changeBreakdown;
    }
}
