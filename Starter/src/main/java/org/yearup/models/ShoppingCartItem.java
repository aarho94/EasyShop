package org.yearup.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public class ShoppingCartItem {
    private Product product;
    private int quantity = 1;
    private BigDecimal discountPercent = BigDecimal.ZERO;

    public ShoppingCartItem() {
        // Default constructor
    }

    public ShoppingCartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(BigDecimal discountPercent) {
        this.discountPercent = discountPercent;
    }

    @JsonIgnore
    public int getProductId() {
        if (product != null) {
            return product.getProductId();
        }
        return 0; // Handle null product scenario
    }

    public BigDecimal getLineTotal() {
        if (product == null) {
            throw new IllegalStateException("Product cannot be null.");
        }

        BigDecimal basePrice = product.getPrice();
        BigDecimal quantityDecimal = new BigDecimal(quantity);

        BigDecimal subTotal = basePrice.multiply(quantityDecimal);
        BigDecimal discountAmount = subTotal.multiply(discountPercent);

        return subTotal.subtract(discountAmount);
    }
}

