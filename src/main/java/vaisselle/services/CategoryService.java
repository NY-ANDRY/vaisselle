package vaisselle.services;

import org.springframework.stereotype.Service;
import vaisselle.models.tables.Category;
import vaisselle.repositories.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category getCategory(Long idCategory) {
        return categoryRepository.findById(idCategory).orElse(null);
    }

    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long idCategory) {
        categoryRepository.deleteById(idCategory);
    }
}
