package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin
public class CategoriesController
{
    private CategoryDao categoryDao;
    private ProductDao productDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao, ProductDao productDao)
    {
        this.categoryDao = categoryDao;
        this.productDao = productDao;
    }

    // getting all categorys from dao
    @GetMapping("")
    public List<Category> getAll()
    {
        try {
            return categoryDao.getAllCategories();
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oops... our bad.");
        }
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable int id)
    {
        Category category = categoryDao.getById(id);
        if(category == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return category;
    }

    @GetMapping("{categoryId}/products")
    public List<Product> getProductsById(@PathVariable int categoryId)
    {
        try {
            return productDao.listByCategoryId(categoryId);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oops... our bad.");
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category)
    {
        try {
            return categoryDao.create(category);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oops...our bad");
        }
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(@PathVariable int id, @RequestBody Category category)
    {
        try {
            categoryDao.update(id, category);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oops...our bad");
        }
    }


    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable int id)
    {
        try {
            categoryDao.delete(id);
        }
        catch (Exception ex)
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Oops...our bad");
        }
    }
}

