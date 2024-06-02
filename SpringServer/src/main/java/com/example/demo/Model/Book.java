package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.Generated;

import java.util.List;
import java.util.Set;

@Entity
public class Book
{
        //izdavach, broj strana,
        //+auto generated

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        String title;
        String description;
        String author;
        Set<Category> category;
        Integer releaseYear;
        String publisher;
        Integer bookSize;
        String coverPageImageName;
        String pdfFileName;



        public Book() {

        }
        public Book(Integer id, String title, String description, String author, Set<Category> category, Integer releaseYear,String pdfFileName,
        String publisher, Integer bookSize) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.author = author;
                this.category = category;
                this.releaseYear = releaseYear;
                this.pdfFileName = pdfFileName;
                this.publisher = publisher;
                this.bookSize = bookSize;
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




}
