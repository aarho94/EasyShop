package org.yearup.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.ProductDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.data.UserDao;
import org.yearup.models.ShoppingCart;
import org.yearup.models.User;

import java.security.Principal;

// convert this class to a REST controller
// only logged in users should have access to these actions
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
        // Get the logged-in user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        ShoppingCart cart = shoppingCartDao.findByUserId(user.getUserId());
        return cart;
    }

    @PostMapping("/products/{productId}")
    public void addProductToCart(@PathVariable int productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        ShoppingCartItem item = shoppingCartDao.findByUserIdAndProductId(user.getUserId(), productId);
        if (item == null) {
            shoppingCartDao.addProductToCart(user.getUserId(), productId, 1);
        } else {
            shoppingCartDao.updateProductQuantity(user.getUserId(), productId, item.getQuantity() + 1);
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

        ShoppingCartItem item = shoppingCartDao.findByUserIdAndProductId(user.getUserId(), productId);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found in cart");
        }

        shoppingCartDao.updateProductQuantity(user.getUserId(), productId, quantity);
    }

    @DeleteMapping
    public void clearCart() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        shoppingCartDao.clearCart(user.getUserId());
    }
}

