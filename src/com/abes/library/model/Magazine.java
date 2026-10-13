package com.abes.library.model;

public class Magazine extends LibraryItem {
    private final int issueNumber;

    public Magazine(String title, int year, int issueNumber) {
        super(title, year);
        if (issueNumber < 1) throw new IllegalArgumentException("Issue number must be positive");
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() { return issueNumber; }
    @Override public double getLateFeePerDay() { return 1.0; }
    @Override public String getType() { return "Magazine"; }
    @Override public String toString() { return super.toString() + " | Issue: " + issueNumber; }
}
