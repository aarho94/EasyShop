package org.yearup.data.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yearup.data.ProductDao;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MySqlProductDao implements ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MySqlProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Product> getAll() {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, this::mapRowToProduct);
    }

    @Override
    public Product getById(int id) {
        String sql = "SELECT * FROM products WHERE productId = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToProduct, id);
    }

    @Override
    public Product create(Product product) {
        String sql = "INSERT INTO products (name, price, categoryId, description, color, stock, isFeatured, imageUrl) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getCategoryId(),
                product.getDescription(), product.getColor(), product.getStock(), product.isFeatured(), product.getImageUrl());
        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        product.setProductId(id);
        return product;
    }

    @Override
    public void update(int id, Product product) {
        String sql = "UPDATE products SET name = ?, price = ?, categoryId = ?, description = ?, color = ?, stock = ?, isFeatured = ?, imageUrl = ? WHERE productId = ?";
        jdbcTemplate.update(sql, product.getName(), product.getPrice(), product.getCategoryId(),
                product.getDescription(), product.getColor(), product.getStock(), product.isFeatured(), product.getImageUrl(), id);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM products WHERE productId = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Product> getProductsByCategoryId(int categoryId) {
        String sql = "SELECT * FROM products WHERE categoryId = ?";
        return jdbcTemplate.query(sql, this::mapRowToProduct, categoryId);
    }

    @Override
    public List<Product> search(Integer categoryId, BigDecimal minPrice, BigDecimal maxPrice, String name) {
        StringBuilder sql = new StringBuilder("SELECT * FROM products WHERE 1=1");
        if (categoryId != null) {
            sql.append(" AND categoryId = ").append(categoryId);
        }
        if (minPrice != null) {
            sql.append(" AND price >= ").append(minPrice);
        }
        if (maxPrice != null) {
            sql.append(" AND price <= ").append(maxPrice);
        }
        if (name != null && !name.isEmpty()) {
            sql.append(" AND name LIKE '%").append(name).append("%'");
        }
        return jdbcTemplate.query(sql.toString(), this::mapRowToProduct);
    }

    public Product mapRowToProduct(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getInt("productId"),
                rs.getString("name"),
                rs.getBigDecimal("price"),
                rs.getInt("categoryId"),
                rs.getString("description"),
                rs.getString("color"),
                rs.getInt("stock"),
                rs.getBoolean("isFeatured"),
                rs.getString("imageUrl")
        );
    }
}




