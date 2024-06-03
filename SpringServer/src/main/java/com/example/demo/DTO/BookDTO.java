package com.example.demo.DTO;

import com.example.demo.Model.Book;
import com.example.demo.Model.Category;
import com.example.demo.Model.CategoryName;
import java.util.Set;
import java.util.stream.Collectors;

public class BookDTO {
    Integer id;
    String title;
    String description;
    String author;
    Set<CategoryName> categoryNames;
    Integer releaseYear;
    String publisher;
    Integer bookSize;
    String coverPageImageName;
    String pdfFileName;


    public BookDTO() {
    }
    public BookDTO(Book book,String imgRoot, String pdfRoot)
    {
        this.id = book.getId();
        this.title = book.getTitle();
        this.description = book.getDesc();
        this.author = book.getAuthor();
        this.categoryNames = book.getCategory().stream().map(Category::getName)
                .collect(Collectors.toSet());
        this.releaseYear = book.getReleaseYear();
        this.coverPageImageName = imgRoot + book.getCoverPageImageName();
        this.pdfFileName = pdfRoot + book.getPdfFileName();
        this.publisher = book.getPublisher();
        this.bookSize = book.getBookSize();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Set<CategoryName> getCategoryNames() {
        return categoryNames;
    }

    public void setCategoryNames(Set<CategoryName> categoryNames) {
        this.categoryNames = categoryNames;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getBookSize() {
        return bookSize;
    }

    public void setBookSize(Integer bookSize) {
        this.bookSize = bookSize;
    }

    public String getCoverPageImageName() {
        return coverPageImageName;
    }

    public void setCoverPageImageName(String coverPageImageName) {
        this.coverPageImageName = coverPageImageName;
    }

    public String getPdfFileName() {
        return pdfFileName;
    }

    public void setPdfFileName(String pdfFileName) {
        this.pdfFileName = pdfFileName;
    }
}
