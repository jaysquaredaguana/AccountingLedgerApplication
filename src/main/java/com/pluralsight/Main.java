package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    // Shared Scanner for all methods in Main
    public static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        StartupScreen.display();
        StartupScreen.animateSeparator();
        String choice;

        do {
            System.out.println();
            TerminalUI.printSeparator(67);
            System.out.println("""
            \s                 GALACTIC LEDGER
            \s                 COMMAND CENTER
                            """);
            TerminalUI.printSeparator(67);
            System.out.println("""
                \s      [D] CREDIT INBOUND    (Add Deposit)
                \s      [P] CREDIT OUTBOUND   (Make Payment)
                \s      [L] TRANSACTION ARCHIVE   (Ledger)
                \s      [X] SHUT DOWN SYSTEM
                """);
            TerminalUI.printSeparator(67);
            System.out.print("""
                SYSTEM STATUS: ONLINE
                Select command:\s""");

            choice = read.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> Ledger.display();
                case "X" -> System.out.print("""
        
                ================================================
                           GALACTIC LEDGER OFFLINE
                        Safe travels, space traveler.
                ================================================
                """);
                default -> System.out.println("Invalid selection. Please try again.");
            }

        } while (!choice.equals("X"));

        read.close();
    }

    public static void addDeposit() {
        System.out.print("""

        ================================================
                       CREDIT INBOUND
                      DEPOSIT TERMINAL
        ================================================
        """);
        Transaction transaction = createTransaction(); // implement helper method here
        transaction.setAmount(Math.abs(transaction.getAmount())); // positive transaction
        if (TransactionFileManager.saveTransaction(transaction)) { // check operation with boolean method return
            System.out.println("[R2-67] Beep boop. Credits secured!");
        }
    }

    public static void makePayment() {
        System.out.print("""

        ================================================
                      CREDIT OUTBOUND
                       PAYMENT TERMINAL
        ================================================
        """);
        Transaction transaction = createTransaction(); // reuse helper method
        transaction.setAmount(-Math.abs(transaction.getAmount())); // negative transaction
        if (TransactionFileManager.saveTransaction(transaction)) { // check operation success
            System.out.println("[R2-67] Payment recorded. Wallet stability questionable.");
        }
    }

    // Collect transaction information
    public static Transaction createTransaction() {
        // Capture current date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        // Themed user prompts but still clear
        System.out.print("Transaction description: ");
        String description = read.nextLine();

        System.out.print("Vendor / source: ");
        String vendor = read.nextLine();

        // simple numeric input validation
        double amount;
        while (true) {
            System.out.print("Enter amount: ");
            try {
                amount = Double.parseDouble(read.nextLine());
                if (amount > 0) {
                    break; // valid escape
                }
                // transaction +/- handled in display, but would like to force nonzero transactions
                System.out.println("Please enter an amount greater than zero.");

            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Please enter a numeric value.");
            }
        }
        return new Transaction(date, time, description, vendor, amount);
    }
}