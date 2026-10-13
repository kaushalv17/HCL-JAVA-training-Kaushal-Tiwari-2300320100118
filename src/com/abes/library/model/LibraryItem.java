package com.abes.library.model;

import java.util.Objects;

public abstract class LibraryItem implements Borrowable, Comparable<LibraryItem> {
    private static int nextId = 1001;
    private final String itemId;
    private final String title;
    private final int year;
    private boolean available = true;

    public LibraryItem(String title, int year) {
        this(String.valueOf(nextId++), title, year);
    }

    public LibraryItem(String itemId, String title, int year) {
        if (itemId == null || itemId.trim().isEmpty()) throw new IllegalArgumentException("Item ID cannot be blank");
        if (title == null || title.trim().isEmpty()) throw new IllegalArgumentException("Title cannot be blank");
        int currentYear = java.time.Year.now().getValue();
        if (year < 1900 || year > currentYear) throw new IllegalArgumentException("Year must be between 1900 and " + currentYear);
        this.itemId = itemId.trim();
        this.title = title.trim();
        this.year = year;
    }

    public String getItemId() { return itemId; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    @Override public boolean isAvailable() { return available; }

    @Override
    public void borrowItem() throws com.abes.library.exception.ItemAlreadyBorrowedException {
        if (!available) throw new com.abes.library.exception.ItemAlreadyBorrowedException("Item is already borrowed: " + title);
        available = false;
    }

    @Override public void returnItem() { available = true; }
    public abstract String getType();

    @Override
    public int compareTo(LibraryItem other) {
        return this.title.compareToIgnoreCase(other.title);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LibraryItem)) return false;
        LibraryItem other = (LibraryItem) obj;
        return itemId.equals(other.itemId);
    }

    @Override public int hashCode() { return Objects.hash(itemId); }

    @Override
    public String toString() {
        return getType() + " | ID: " + itemId + " | Title: " + title + " | Year: " + year + " | " + (available ? "Available" : "Borrowed");
    }
}
