package org.yearup.data;

import org.yearup.models.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> getAll();
    Product getById(int id);
    Product create(Product product);
    void update(int id, Product product);
    void delete(int id);

    List<Product> getProductsByCategoryId(int categoryId);

    List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String name);

    Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException;
}


