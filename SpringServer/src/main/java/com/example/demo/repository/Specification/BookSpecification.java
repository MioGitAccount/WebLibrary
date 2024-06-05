package com.example.demo.repository.Specification;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class BookSpecification {
    public static Specification<Book> hasTitle(String title) {
        return (root, query, criteriaBuilder) ->
                title == null ? null : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                author == null ? null : criteriaBuilder.like(root.get("author"), "%" + author + "%");
    }

    public static Specification<Book> hasCategory(Set<Category> categories) {
        return (root, query, criteriaBuilder) ->
                categories == null ? null : root.join("category").in(categories);
    }

    public static Specification<Book> hasReleaseYear(Integer releaseYear) {
        return (root, query, criteriaBuilder) ->
                releaseYear == null ? null : criteriaBuilder.equal(root.get("releaseYear"), releaseYear);
    }

    public static Specification<Book> hasPublisher(String publisher) {
        return (root, query, criteriaBuilder) ->
                publisher == null ? null : criteriaBuilder.like(root.get("publisher"), "%" + publisher + "%");
    }
}
