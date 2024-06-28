package org.yearup.data;

import org.yearup.models.ShoppingCart;

public interface ShoppingCartDao {

    ShoppingCart findByUserId(int userId);

    ShoppingCart findByUserIdAndProductId(int userId, int productId);

    void addProductToCart(int userId, int productId, int quantity);

    void updateProductQuantity(int userId, int productId, int quantity);

    void clearCart(int userId);

    // Other methods as needed
}

