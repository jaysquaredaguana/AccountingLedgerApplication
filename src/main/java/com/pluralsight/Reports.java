package com.pluralsight;

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

    public static void monthToDate() {
        // Month To Date report
    }

    public static void previousMonth() {
        // Previous Month report
    }

    public static void yearToDate() {
        // Year To Date report
    }

    public static void previousYear() {
        // Previous Year report
    }

    public static void searchByVendor() {
        // Vendor search
    }

    public static void customSearch() {
        // Custom search
    }
}
