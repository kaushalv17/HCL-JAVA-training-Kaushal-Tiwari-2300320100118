package com.abes.library.model;

public class Book extends LibraryItem {
    private final String author;
    private final String isbn;

    public Book(String title, int year, String author, String isbn) {
        super(title, year);

        if (author == null || author.trim().isEmpty()) throw new IllegalArgumentException("Author cannot be blank");
        if (isbn == null || isbn.trim().isEmpty()) throw new IllegalArgumentException("ISBN cannot be blank");
        this.author = author.trim();
        this.isbn = isbn.trim();

        

        
        
    }

    public Book(String itemId, String title, int year, String author, String isbn) {
        super(itemId, title, year);
        if (author == null || author.trim().isEmpty()) throw new IllegalArgumentException("Author cannot be blank");
        if (isbn == null || isbn.trim().isEmpty()) throw new IllegalArgumentException("ISBN cannot be blank");
        this.author = author.trim();
        this.isbn = isbn.trim();
    }

    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    @Override public double getLateFeePerDay() { return 2.0; }
    @Override public String getType() { return "Book"; }

    @Override
    public String toString() { return super.toString() + " | Author: " + author + " | ISBN: " + isbn; }
}
