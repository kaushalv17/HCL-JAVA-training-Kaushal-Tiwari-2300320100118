package com.abes.library.model;

public interface Borrowable {
    boolean isAvailable();
    void borrowItem() throws com.abes.library.exception.ItemAlreadyBorrowedException;
    void returnItem();
    double getLateFeePerDay();
}
