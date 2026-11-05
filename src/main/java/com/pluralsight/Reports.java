package com.pluralsight;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Reports {
    public static void display() {
        String choice;
        do {
            System.out.print("""
    
            ================================================
                          GALACTIC LEDGER
                           MISSION REPORTS
            ================================================
    
              [1] MONTH TO DATE
              [2] PREVIOUS MONTH
              [3] YEAR TO DATE
              [4] PREVIOUS YEAR
              [5] SEARCH BY VENDOR
              [6] CUSTOM SEARCH
              [0] RETURN TO ARCHIVE
    
            ------------------------------------------------
              REPORTING SYSTEM ONLINE
              Select report: """);

            choice = Main.read.nextLine().trim();

            switch (choice) {
                case "1" -> monthToDate();
                case "2" -> previousMonth();
                case "3" -> yearToDate();
                case "4" -> previousYear();
                case "5" -> searchByVendor();
                case "6" -> customSearch();
                case "0" -> System.out.println("Returning to Ledger...");
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } while (!choice.equals("0"));
    }

    // Month To Date report
    public static void monthToDate() {
        LocalDate today = LocalDate.now();

        LocalDate startDate = today.withDayOfMonth(1);
        LocalDate endDate = today;

        displayReport(startDate, endDate);
    }

    // Previous Month report
    public static void previousMonth() {
        YearMonth previousMonth = YearMonth.now().minusMonths(1);

        LocalDate startDate = previousMonth.atDay(1);
        LocalDate endDate = previousMonth.atEndOfMonth();

        displayReport(startDate, endDate);
    }

    // Year To Date report
    public static void yearToDate() {
        LocalDate today = LocalDate.now();

        LocalDate startDate = today.withDayOfYear(1);
        LocalDate endDate = today;

        displayReport(startDate, endDate);
    }

    // Previous Year report
    public static void previousYear() {
        Year previousYear = Year.now().minusYears(1);
        // handles leap year situations better
        LocalDate startDate = previousYear.atDay(1);
        LocalDate endDate = previousYear.atMonth(12).atEndOfMonth();

        displayReport(startDate, endDate);
    }

    // display method shared by calls in this Reports class
    public static void displayReport(LocalDate startDate, LocalDate endDate) {
        ArrayList<Transaction> transactions =
                TransactionService.getTransactionsByDateRange(
                        startDate, endDate
                );
        displayTransactions(transactions);
    }

    // Vendor search
    public static void searchByVendor() {
        System.out.print("Enter vendor name: ");
        String vendor = Main.read.nextLine().trim();

        if (vendor.isEmpty()) {
            System.out.println("Vendor name cannot be empty.");
            return;
        }
        // TransactionService method does the search, Reports class calls it
        ArrayList<Transaction> transactions = TransactionService.searchByVendor(vendor);
        displayTransactions(transactions);
    }

    // Reusable transaction table
    public static void displayTransactions(ArrayList<Transaction> transactions) {
        if (transactions.isEmpty()) {
            System.out.println();
            System.out.println("No transactions found.");
            return;
        }

        System.out.println();
        System.out.println("========================= GALACTIC LEDGER =========================");
        System.out.printf("%-12s %-10s %-24s %-24s %12s%n", // column width format and alignment
                "DATE", "TIME", "DESCRIPTION", "VENDOR", "AMOUNT"); // table field headers
        System.out.println("--------------------------------------------------------------------");
        // reuse columns and fill fields
        for (Transaction transaction : transactions) {
            System.out.printf("%-12s %-10s %-24s %-24s %12.2f%n",
                    transaction.getDate(),
                    transaction.getTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")), // forcing ISO default
                    transaction.getDescription(),
                    transaction.getVendor(),
                    transaction.getAmount());
        }
        // footer border and counter display
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Total Transactions: " + transactions.size());
    }

    public static void customSearch() {
        System.out.println("\nFor custom search, please type your desired value OR enter to leave blank.");

        // initializing the variables before collecting input
        String input; // will use for validation
        LocalDate startDate = null;
        LocalDate endDate = null;
        String description = ""; // use empty strings for omitted text criteria
        String vendor = "";
        Double amount = null;

        // Start date collection
        boolean validStartDate = false;
        while (!validStartDate) {
            System.out.print("Start date (YYYY-MM-DD): ");
            input = Main.read.nextLine().trim();
            // flag valid since can be optionally blank
            if (input.isBlank()) {
                validStartDate = true;
            } else {
                try {
                    startDate = LocalDate.parse(input);
                    validStartDate = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date. Please use YYYY-MM-DD.");
                }
            }
        }

        // End date collection
        boolean validEndDate = false;
        while (!validEndDate) {
            System.out.print("End date (YYYY-MM-DD): ");
            input = Main.read.nextLine().trim();
            // blank is valid, optional
            if (input.isBlank()) {
                validEndDate = true;
            } else {
                try {
                    endDate = LocalDate.parse(input);
                    validEndDate = true;
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date. Please use YYYY-MM-DD.");
                }
            }
        }

        // If collecting both dates, check the start date isn't after the end date.
        if (startDate != null && endDate != null &&
                startDate.isAfter(endDate)) {
            System.out.println("Start date cannot be after end date.");
            return;
        }

        System.out.print("Description: ");
        description = Main.read.nextLine().trim();

        System.out.print("Vendor: ");
        vendor = Main.read.nextLine().trim();

        // Amount collection
        boolean validAmount = false;
        while (!validAmount) {
            System.out.print("Amount (or enter blank to skip): ");
            input = Main.read.nextLine().trim();
            // checks the escape condition and leaves value untouched
            if (input.isBlank()) {
                validAmount = true;
            } else {
                try {
                    amount = Double.parseDouble(input);
                    validAmount = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numerical value.");
                }
            }
        }
        SearchCriteria criteria = new SearchCriteria(startDate, endDate, description, vendor, amount);
        displayTransactions(TransactionService.customSearch(criteria));
    }
}
