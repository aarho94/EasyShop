package org.yearup.models;

import org.springframework.stereotype.Repository;
import org.yearup.data.CategoryDao;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao
{

    // Dummy data for illustration purposes
    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getById(int id) {
        return categories.stream()
                .filter(category -> category.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Category create(Category category) {
        categories.add(category);
        return category;
    }

    @Override
    public void update(int id, Category category) {
        int index = -1;
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getId() == id) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            categories.set(index, category);
        }
    }

    @Override
    public void delete(int id) {
        categories.removeIf(category -> category.getId() == id);
    }
}

