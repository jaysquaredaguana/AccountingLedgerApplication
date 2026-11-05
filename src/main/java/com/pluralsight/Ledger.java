package com.pluralsight;

public class Ledger {
    public static void display() {
        String choice;
        do {
            System.out.print("""
   \s
            ================================================
                          GALACTIC LEDGER
                        TRANSACTION ARCHIVE
            ================================================
   \s
              [A] ALL TRANSACTIONS
              [D] DEPOSIT RECORDS
              [P] PAYMENT RECORDS
              [R] MISSION REPORTS
              [H] RETURN TO HOME
   \s
            ------------------------------------------------
              SYSTEM AWAITING INPUT
              Select destination:\s""");

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
        Reports.displayTransactions(TransactionService.getAllTransactions());
    }

    public static void displayDeposits() {
        Reports.displayTransactions(TransactionService.getDeposits());
    }

    public static void displayPayments() {
        Reports.displayTransactions(TransactionService.getPayments());
    }
}
