package com.j2ee.gestionVente.service;

import com.j2ee.gestionVente.entities.Category;
import com.j2ee.gestionVente.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository ;


    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> saveAllCategory(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> updateAllCategory(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public void deleteCategoryById(Long id) {

        categoryRepository.deleteById(id);
    }

    @Override
    public void deleteCategory(Category category) {

        categoryRepository.delete(category);
    }

    @Override
    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }
}
