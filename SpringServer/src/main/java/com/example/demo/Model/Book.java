package com.example.demo.Model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Book
{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        String title;
        String description;
        String author;

        @ManyToMany
        @JoinTable(
                name = "book_categories",
                joinColumns = @JoinColumn(name = "book_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id")
        )
        Set<Category> category;
        Integer releaseYear;
        String publisher;
        Integer bookSize;
        String coverPageImageName;
        String pdfFileName;
        LocalDate dateAdded;
        String language;


        public Book() {

        }
        public Book(Integer id, String title, String description, String author, Set<Category> category, Integer releaseYear, String coverPageImageName,String pdfFileName,
                    String publisher, Integer bookSize, LocalDate dateAdded, String language) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.author = author;
                this.category = category;
                this.releaseYear = releaseYear;
                this.coverPageImageName = coverPageImageName;
                this.pdfFileName = pdfFileName;
                this.publisher = publisher;
                this.bookSize = bookSize;
                this.dateAdded = dateAdded;
                this.language = language;
        }
        //without id
        public Book(String title, String description, String author, Set<Category> category,Integer releaseYear,String coverPageImageName, String pdfFileName,
                    String publisher, Integer bookSize,LocalDate dateAdded, String language) {
                this.title = title;
                this.description = description;
                this.author = author;
                this.category = category;
                this.releaseYear = releaseYear;
                this.coverPageImageName = coverPageImageName;
                this.pdfFileName = pdfFileName;
                this.publisher = publisher;
                this.bookSize = bookSize;
                this.dateAdded = dateAdded;
                this.language = language;
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

        public String getDesc() {
                return description;
        }

        public void setDesc(String description) {
                this.description = description;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public Set<Category> getCategory() {
                return category;
        }

        public void setCategory(Set<Category> category) {
                this.category = category;
        }

        public Integer getReleaseYear() {
                return releaseYear;
        }

        public void setReleaseYear(Integer releaseYear) {
                this.releaseYear = releaseYear;
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
        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getDateAdded() {
                return dateAdded;
        }

        public void setDateAdded(LocalDate dateAdded) {
                this.dateAdded = dateAdded;
        }

        public String getLanguage() {
                return language;
        }

        public void setLanguage(String language) {
                this.language = language;
        }




}
