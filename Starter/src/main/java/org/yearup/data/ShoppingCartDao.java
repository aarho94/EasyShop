package org.yearup.data;

import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;

public interface ShoppingCartDao {
    ShoppingCart findByUserId(int userId);

    ShoppingCartItem findByUserIdAndProductId(int userId, int productId);

    void addProductToCart(int userId, int productId, int quantity);

    void updateProductQuantity(int userId, int productId, int quantity);

    void clearCart(int userId);
}


