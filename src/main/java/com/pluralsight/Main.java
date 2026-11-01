package com.pluralsight;

import java.util.Scanner;

public class Main {
    // Shared Scanner for all methods in Main
    private static final Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
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
                case "L" -> displayLedger();
                case "X" -> System.out.println("Thank you for using the application!");
                default -> System.out.println("Invalid selection. Please try again.");
            }

        } while (!choice.equals("X"));

        read.close();
    }
}