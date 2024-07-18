package com.example.demo.repository.Specification;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
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

    //select book_id, count(*) from book_categories where category_id in (11,18) group by book_id having count(book_id)
    // = 2;
    public static Specification<Book> hasCategory(Set<Category> categories) {
        return (root, query, criteriaBuilder) ->
        {
            if (categories == null || categories.isEmpty()) {
                return null;
            }

            Join<Book, Category> categoryJoin = root.join("category", JoinType.INNER);

            query.groupBy(root.get("id"));
            query.having(criteriaBuilder.equal(criteriaBuilder.countDistinct(categoryJoin.get("id")), categories.size()));

            CriteriaBuilder.In<Long> inClause = criteriaBuilder.in(categoryJoin.get("id"));
            categories.forEach(category -> inClause.value(category.getId()));

            return inClause;
        };

    }

    public static Specification<Book> hasReleaseYear(Integer releaseYear) {
        return (root, query, criteriaBuilder) ->
                releaseYear == null ? null : criteriaBuilder.equal(root.get("releaseYear"), releaseYear);
    }

    public static Specification<Book> hasPublisher(String publisher) {
        return (root, query, criteriaBuilder) ->
                publisher == null ? null : criteriaBuilder.like(root.get("publisher"), "%" + publisher + "%");
    }

    public static Specification<Book> afterDate(LocalDate date) {
        return (root, query, criteriaBuilder) ->
                date == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("dateAdded"), date);
    }

    public static Specification<Book> hasLanguage(String language) {
        return (root, query, criteriaBuilder) ->
                language == null ? null : criteriaBuilder.like(root.get("language"), "%" + language + "%");
    }
}
