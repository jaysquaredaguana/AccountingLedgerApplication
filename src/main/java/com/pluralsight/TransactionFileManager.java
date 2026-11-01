package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionFileManager {

    private static final String FILE_NAME = "transactions.csv";

    // Append transaction to CSV file
    public static void saveTransaction(Transaction transaction) {
        // Declare and initialize try with resources
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)
        ) {

            bufferedWriter.write(transaction.toString());
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.out.println("Error saving transaction: " + e.getMessage());
        }
    }
}
