package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionFileManager {

    private static final String FILE_NAME = "transactions.csv";

    // Append transaction to CSV file
    public static void saveTransaction(Transaction transaction) {
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
        }
    }

    // Read transactions from CSV file
    public static ArrayList<Transaction> readTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        try (
                FileReader fileReader = new FileReader(FILE_NAME);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            String line = bufferedReader.readLine(); // initialize and discard file header
            while ((line = bufferedReader.readLine()) != null) {
                String[] fields = line.split("\\|");

                LocalDate date = LocalDate.parse(fields[0]);
                LocalTime time = LocalTime.parse(fields[1]);
                String description = fields[2];
                String vendor = fields[3];
                double amount = Double.parseDouble(fields[4]);

                Transaction transaction = new Transaction(date, time, description, vendor, amount);
                transactions.add(transaction);
            }

        } catch (IOException e) {
            System.out.println("[STORAGE ALERT] Unable to retrieve transaction records: " + e.getMessage());
        }
        return transactions;
    }
}
