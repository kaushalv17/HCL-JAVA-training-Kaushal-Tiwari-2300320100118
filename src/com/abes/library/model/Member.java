package com.abes.library.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Member {
    private final String memberId;
    private final String name;
    private final String email;
    private final List<LibraryItem> borrowedItems = new ArrayList<>();

    public Member(String memberId, String name, String email) {
        if (memberId == null || memberId.trim().isEmpty()) throw new IllegalArgumentException("Member ID cannot be blank");
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be blank");
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) throw new IllegalArgumentException("Invalid email");
        this.memberId = memberId.trim();
        this.name = name.trim();
        this.email = email.trim();
    }

    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<LibraryItem> getBorrowedItems() { return Collections.unmodifiableList(borrowedItems); }

    public void addBorrowedItem(LibraryItem item) { borrowedItems.add(item); }
    public void removeBorrowedItem(LibraryItem item) { borrowedItems.remove(item); }
    @Override public String toString() { return memberId + " | " + name + " | " + email + " | Borrowed: " + borrowedItems.size(); }
}
