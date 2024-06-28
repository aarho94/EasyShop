package org.yearup.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private Map<Integer, ShoppingCartItem> items = new HashMap<>();

    public Map<Integer, ShoppingCartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, ShoppingCartItem> items) {
        this.items = items;
    }

    public boolean contains(int productId) {
        return items.containsKey(productId);
    }

    public void add(int productId, int quantity) {
        if (items.containsKey(productId)) {
            ShoppingCartItem item = items.get(productId);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            ShoppingCartItem newItem = new ShoppingCartItem(productId, quantity);
            items.put(productId, newItem);
        }
    }

    public void updateQuantity(int productId, int quantity) {
        if (items.containsKey(productId)) {
            ShoppingCartItem item = items.get(productId);
            item.setQuantity(quantity);
        }
    }

    public void remove(int productId) {
        items.remove(productId);
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (ShoppingCartItem item : items.values()) {
            total = total.add(item.getLineTotal());
        }
        return total;
    }
}

