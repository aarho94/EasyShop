package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;

import java.util.Map;

@RestController
@RequestMapping("/cart")
public class ShoppingCartController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private ProductDao productDao;

    @GetMapping
    public ShoppingCart getCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        ShoppingCart cart = shoppingCartDao.findByUserId(user.getId());
        return cart;
    }

    @PostMapping("/products/{productId}")
    public void addProductToCart(@PathVariable int productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        ShoppingCartItem item = shoppingCartDao.findByUserIdAndProductId(user.getId(), productId);
        if (item == null) {
            shoppingCartDao.addProductToCart(user.getId(), productId, 1);
        } else {
            shoppingCartDao.updateProductQuantity(user.getId(), productId, item.getQuantity() + 1);
        }
    }

    @PutMapping("/products/{productId}")
    public void updateProductQuantityInCart(@PathVariable int productId, @RequestBody Map<String, Integer> body) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        Integer quantity = body.get("quantity");
        if (quantity == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity is required");
        }

        ShoppingCartItem item = shoppingCartDao.findByUserIdAndProductId(user.getId(), productId);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in cart");
        }

        shoppingCartDao.updateProductQuantity(user.getId(), productId, quantity);
    }

    @DeleteMapping
    public void clearCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        shoppingCartDao.clearCart(user.getId());
    }
}

