package com.example.demo.repository.DataInitializers;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.CategoryName;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        initializeCategories();
        initializeBooks();
    }

    private void initializeCategories() {
        if (categoryRepository.count() == 0) {
            Arrays.stream(CategoryName.values()).forEach(name -> {
                Category category = new Category();
                category.setName(name);
                categoryRepository.save(category);
            });
        }
    }

    private void initializeBooks(){
        if (bookRepository.count() == 0) {
            Set<Category> categories1 = new HashSet<>();
            categories1.add(categoryRepository.findByName(CategoryName.Comic_and_Graphic_Books));
            categories1.add(categoryRepository.findByName(CategoryName.Literature));
            Book book1 = new Book("The Great Gatsby", "A novel about the American dream", "F. Scott Fitzgerald",
                    categories1, 1925,"great_gatsby.jpg", "great_gatsby.pdf", "Scribner", 218);
            bookRepository.save(book1);

            Set<Category> categories2 = new HashSet<>();
            categories2.add(categoryRepository.findByName(CategoryName.Science));
            Book book2 = new Book("A Brief History of Time", "A popular-science book on cosmology", "Stephen Hawking",
                    categories2, 1988, "brief_history.jpg","brief_history.pdf", "Bantam Books", 256);
            bookRepository.save(book2);

            Set<Category> categories3 = new HashSet<>();
            categories3.add(categoryRepository.findByName(CategoryName.Business));
            categories3.add(categoryRepository.findByName(CategoryName.Biography_and_Autobiography));
            Book book3 = new Book("Steve Jobs", "Biography of Steve Jobs", "Walter Isaacson",
                    categories3, 2011,"steve_jobs.jpg", "steve_jobs.pdf", "Simon & Schuster", 656);
            bookRepository.save(book3);

            Set<Category> categories4 = new HashSet<>();
            categories4.add(categoryRepository.findByName(CategoryName.History));
            categories4.add(categoryRepository.findByName(CategoryName.Sociology));
            Book book4 = new Book("Sapiens: A Brief History of Humankind", "A book on the history of humankind",
                    "Yuval Noah Harari", categories4, 2011, "sapiens.jpg", "sapiens.pdf", "Harper", 498);
            bookRepository.save(book4);

            Set<Category> categories5 = new HashSet<>();
            categories5.add(categoryRepository.findByName(CategoryName.Science));
            Book book5 = new Book("Clean Code", "A Handbook of Agile Software Craftsmanship", "Robert C. Martin",
                    categories5, 2008, "clean_code.jpg","clean_code.pdf", "Prentice Hall", 464);
            bookRepository.save(book5);
        }
    }
}
