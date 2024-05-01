package com.lebilotta.pubsubmessaging.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "BOOK")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private Integer pages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "\n" +
            "Book: { \n" +
            "\t" + "author: " + this.author + "\n" +
            "\t" + "title: " + this.title + "\n" +
            "\t" + "pages: " + this.pages + "\n" +
            "}";
    }
}
