package com.pluralsight;

public class Ledger {
    public static void display() {
        String choice;
        do {
            System.out.println();
            System.out.println("========== LEDGER ==========");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");
            System.out.print("Select an option: ");

            choice = Main.read.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A" -> displayAll();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> Reports.display();
                case "H" -> System.out.println("Returning to Home Screen...");
                default -> System.out.println("Invalid selection. Please try again.");
            }
        } while (!choice.equals("H"));
    }

    public static void displayAll() {
        // Display all transactions
    }

    public static void displayDeposits() {
        // Display deposits
    }

    public static void displayPayments() {
        // Display payments
    }
}
