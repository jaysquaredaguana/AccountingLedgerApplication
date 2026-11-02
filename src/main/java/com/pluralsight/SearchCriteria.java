package com.pluralsight;

import java.time.LocalDate;

public class SearchCriteria {
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String vendor;
    private boolean filterAmount; // flag to track if user omitted
    private double amount; // primitive double

    public SearchCriteria(LocalDate startDate, LocalDate endDate,
                          String description, String vendor,
                          Double amount) {
        // Note use of Double wrapper to allow null/omitted input
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.vendor = vendor;
        // boolean flag checks user input as a Double
        this.filterAmount = amount != null;
        this.amount = (amount != null) ? amount : 0.0;
        // unbox only if there's a value to avoid null primitive
        // otherwise sets valid value 0.0 and will use flag
    }

    // Getters

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public String getVendor() {
        return vendor;
    }

    public boolean isFilterAmount() {
        return filterAmount;
    }

    public double getAmount() {
        return amount; // return primitive double, while constructor accepts Double
    }
}
