package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Transaction {
    // Transaction attributes
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    // Time formatter to include seconds
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");

    // Constructor
    public Transaction(LocalDate date, LocalTime time,
                       String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    // Getters
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public double getAmount() {
        return amount;
    }

    // Setter
    public void setAmount(double amount) {
        this.amount = amount;
    }

    // Format transaction for file storage
    public String toFileFormat() {
        return date + "|" +
                time.format(TIME_FORMAT) + "|" +
                description + "|" +
                vendor + "|" +
                String.format(java.util.Locale.US, "%.2f", amount);
    }

    // readable transaction representation
    @Override
    public String toString() {
        return description + " | " + vendor + " | " +
                String.format(Locale.US, "%.2f", amount);
    }
}
