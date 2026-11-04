package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    // Shared Scanner for all methods in Main
    public static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        StartupScreen.display();

        String choice;

        do {
            System.out.println();
            System.out.println("========== HOME SCREEN ==========");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");
            System.out.print("Select an option: ");

            choice = read.nextLine().trim().toUpperCase();

            switch (choice) {
                case "D" -> addDeposit();
                case "P" -> makePayment();
                case "L" -> Ledger.display();
                case "X" -> System.out.println("Thank you for using the application!");
                default -> System.out.println("Invalid selection. Please try again.");
            }

        } while (!choice.equals("X"));

        read.close();
    }

    public static void addDeposit() {
        System.out.println();
        System.out.println("========== ADD DEPOSIT ==========");
        Transaction transaction = createTransaction(); // implement helper method here
        transaction.setAmount(Math.abs(transaction.getAmount())); // positive transaction
        TransactionFileManager.saveTransaction(transaction);
        System.out.println("Deposit successfully saved!");
    }

    public static void makePayment() {
        System.out.println();
        System.out.println("========== MAKE PAYMENT ==========");
        Transaction transaction = createTransaction(); // reuse helper method
        transaction.setAmount(-Math.abs(transaction.getAmount())); // negative transaction
        TransactionFileManager.saveTransaction(transaction);
        System.out.println("Payment successfully saved!");
    }

    // Collect transaction information
    public static Transaction createTransaction() {
        // Automatically capture current date and time
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();

        System.out.print("Enter description: ");
        String description = read.nextLine();
        System.out.print("Enter vendor: ");
        String vendor = read.nextLine();
        System.out.print("Enter amount: ");
        double amount = Double.parseDouble(read.nextLine());

        return new Transaction(date, time, description, vendor, amount);
    }

    // Ledger Screen
    public static void displayLedger() {
        System.out.println();
        System.out.println("========== LEDGER ==========");
        System.out.println("Ledger functionality coming soon!");
    }
}