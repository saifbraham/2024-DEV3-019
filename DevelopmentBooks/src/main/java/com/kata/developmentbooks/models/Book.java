package com.kata.developmentbooks.models;

public class Book {
    private String bookTitle;
    private String bookAuthor;
    private int publishYear;

    public Book(String bookTitle, String bookAuthor, int publishYear) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.publishYear = publishYear;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

}

