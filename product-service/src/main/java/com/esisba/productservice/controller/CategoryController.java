package com.esisba.productservice.controller;

import com.esisba.productservice.entitiy.Category;
import com.esisba.productservice.entitiy.Product;
import com.esisba.productservice.repository.CategoryRepository;
import com.esisba.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api-v1/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public Collection<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/{idCategory}")
    public Category getCategory(@PathVariable("idCategory") Long idCategory){
        return categoryRepository.findById(idCategory).get();
    }

    @PostMapping("/")
    public Category createCategory(@Valid @RequestBody Category category){
        return categoryRepository.save(category);
    }

    @PutMapping("{idCategory}")
    public Category updateCategory(@PathVariable("idCategory") Long idCategory, @Valid @RequestBody Category category){
        if(categoryRepository.findById(idCategory).isPresent()){
            category.setId(idCategory);
            return categoryRepository.save(category);
        }
        return null;
    }

    @DeleteMapping("{idCategory}")
    public void deleteCategory(@PathVariable("idCategory") Long idCategory){
        if(categoryRepository.findById(idCategory).isPresent()){
            categoryRepository.deleteById(idCategory);
        }
    }

    @GetMapping("/{idCategory}/products")
    public List<Product> getCategoryProducts(@PathVariable("idCategory") Long idCategory){
        Category category = categoryRepository.findById(idCategory).get();
        return productRepository.findProductsByCategory(category);
    }
}
