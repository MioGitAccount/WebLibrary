package com.example.demo.repository;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByName(CategoryName name);
}
