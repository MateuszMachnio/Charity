package pl.coderslab.charity.service.implementations;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.service.interfaces.CategoryService;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("There is no such Category"));
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.getAllByOrderByName();
    }
}
