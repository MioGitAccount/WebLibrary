package com.example.demo.repository.DataInitializers;

import com.example.demo.Model.Category;
import com.example.demo.Model.CategoryName;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryDataInitializer implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values()).forEach(name -> {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            });
        }
    }
}
