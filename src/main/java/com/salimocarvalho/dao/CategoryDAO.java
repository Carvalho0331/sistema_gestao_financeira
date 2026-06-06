package com.salimocarvalho.dao;

import com.salimocarvalho.model.Category;


import java.util.List;

public interface CategoryDAO {
    void createCategory(Category category);
    Category getCategory(int id);
    List<Category> showAllCategories();
    void updateCategory(Category category);
    void deleteCategory(int id);
}
