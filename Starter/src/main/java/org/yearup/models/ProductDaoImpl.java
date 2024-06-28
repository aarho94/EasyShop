package org.yearup.models;

import org.springframework.stereotype.Repository;
import org.yearup.data.ProductDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class ProductDaoImpl implements ProductDao
{

    // Dummy data for illustration purposes
    private List<Product> products = new ArrayList<>();

    @Override
    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getById(int id) {
        return products.stream()
                .filter(product -> product.getProductId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product create(Product product) {
        products.add(product);
        return product;
    }

    @Override
    public void update(int id, Product product) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            products.set(index, product);
        }
    }

    @Override
    public void delete(int id) {
        products.removeIf(product -> product.getProductId() == id);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        List<Product> categoryProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategoryId() == categoryId) {
                categoryProducts.add(product);
            }
        }
        return categoryProducts;
    }

    @Override
    public List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String name)
    {
        return List.of();
    }
}

