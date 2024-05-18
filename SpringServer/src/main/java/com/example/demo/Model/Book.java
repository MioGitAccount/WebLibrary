package com.example.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book
{
        //+auto generated
        @Id
        Integer id;
        String title;
        String decs;
        String author;
        Category category;
        Integer releaseYear;

        public Book() {

        }

        public Book(Integer id, String title, String decs, String author, Category category, Integer releaseYear) {
                this.id = id;
                this.title = title;
                this.decs = decs;
                this.author = author;
                this.category = category;
                this.releaseYear = releaseYear;
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

        public String getDecs() {
                return decs;
        }

        public void setDecs(String decs) {
                this.decs = decs;
        }

        public String getAuthor() {
                return author;
        }

        public void setAuthor(String author) {
                this.author = author;
        }

        public Category getCategory() {
                return category;
        }

        public void setCategory(Category category) {
                this.category = category;
        }

        public Integer getReleaseYear() {
                return releaseYear;
        }

        public void setReleaseYear(Integer releaseYear) {
                this.releaseYear = releaseYear;
        }
}
