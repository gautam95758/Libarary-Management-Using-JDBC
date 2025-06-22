package com.examly.springapp.model;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private float price;
    private boolean available;

    public Book(int bookId, String title, String author, float price, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.available = available;
    }

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
