package pl.coderslab.charity.service.interfaces;

import pl.coderslab.charity.entity.Category;

import java.util.List;

public interface CategoryService {

    Category findById(long id);

    Category saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(long id);

    List<Category> findAllCategories();
}
