package com.pluralsight;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;

public class Reports {
    public static void display() {
        String choice;
        do {
            System.out.println();
            System.out.println("========== REPORTS ==========");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");
            System.out.print("Select an option: ");

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
        // check and handle if no transactions within range
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        // prints all transactions found within range
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public static void searchByVendor() {
        // Vendor search
    }

    public static void customSearch() {
        // Custom search
    }
}
