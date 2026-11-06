package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TransactionFileManager {

    private static final String FILE_NAME = "transactions.csv";

    // Append transaction to CSV file
    public static boolean saveTransaction(Transaction transaction) {
        // Declare and initialize try with resources
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {
            // implement distinct format
            bufferedWriter.write(transaction.toFileFormat());
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.out.println("[STORAGE ALERT] Unable to save transaction: " + e.getMessage());
            return false; // boolean return tracks success/failed operation
        }
        return true; // resources close and operation completes
    }

    // Read transactions from CSV file
    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try (
                FileReader fileReader = new FileReader(FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line = bufferedReader.readLine(); // Skip header
            int lineNumber = 1; // line tracker for any errors to catch

            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++; // continue tracking each line by incrementing

                String[] fields = line.split("\\|", -1); // handles additional pipe escapes

                if (fields.length != 5) { // expecting 5 fields, any other amount detects deviance
                    System.out.println("[STORAGE ALERT] Skipping malformed record at line " + lineNumber);
                    continue;
                }

                try {
                    LocalDate date = LocalDate.parse(fields[0]); // catch this
                    LocalTime time = LocalTime.parse(fields[1]);
                    String description = fields[2];
                    String vendor = fields[3];
                    double amount = Double.parseDouble(fields[4]); // catch this

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);
                    transactions.add(transaction);

                } catch (DateTimeParseException | NumberFormatException e) {
                    System.out.println("[STORAGE ALERT] Skipping invalid record at line " + lineNumber);
                }
            }

        } catch (IOException e) {
            System.out.println("[STORAGE ALERT] Unable to read transaction records: " + e.getMessage());
        }

        return transactions;
    }
}
