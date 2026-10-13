package com.abes.library.model;

public class DVD extends LibraryItem {
    private final int durationMinutes;

    public DVD(String title, int year, int durationMinutes) {
        super(title, year);
        if (durationMinutes < 1) throw new IllegalArgumentException("Duration must be positive");
        this.durationMinutes = durationMinutes;
    }

    public int getDurationMinutes() { return durationMinutes; }
    @Override public double getLateFeePerDay() { return 5.0; }
    @Override public String getType() { return "DVD"; }
    @Override public String toString() { return super.toString() + " | Duration: " + durationMinutes + " minutes"; }
}
