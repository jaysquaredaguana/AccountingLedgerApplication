package com.pluralsight;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class TransactionService {

    // Transaction retrieval and sorting

    // Retrieve all transactions, newest first
    public static ArrayList<Transaction> getAllTransactions() {

        // file manager method call to handle reading, populates our ArrayList for sorting
        ArrayList<Transaction> transactions = TransactionFileManager.readTransactions();
        return sortTransactions(transactions);
    }

    // Sort transactions by date and time, newest first
    public static ArrayList<Transaction> sortTransactions(ArrayList<Transaction> transactions) {
        // create a new ArrayList for sorting (separate from original)
        ArrayList<Transaction> sortedTransactions = new ArrayList<>(transactions);
        // chronological ordering from newest to oldest
        Comparator<Transaction> comparator =
                Comparator.comparing(Transaction::getDate)
                          .thenComparing(Transaction::getTime)
                          .reversed();

        sortedTransactions.sort(comparator);

        return sortedTransactions;
    }

    // Transaction filtering

    public static ArrayList<Transaction> getDeposits() {
        ArrayList<Transaction> deposits = new ArrayList<>();
        for (Transaction transaction : getAllTransactions()) { // this method call returns sorted collection
            if (transaction.getAmount() > 0) { // Deposits are positive amounts
                deposits.add(transaction);
            }
        }
        return deposits;
    }

    public static ArrayList<Transaction> getPayments() {
        ArrayList<Transaction> payments = new ArrayList<>();
        for (Transaction transaction : getAllTransactions()) {
            if (transaction.getAmount() < 0) { // Payments are negative amounts
                payments.add(transaction);
            }
        }
        return payments;
    }

    // date-range lookup REUSABLE method
    // Retrieve transactions within an inclusive date range
    public static ArrayList<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        ArrayList<Transaction> filteredTransactions = new ArrayList<>();
        for (Transaction transaction : getAllTransactions()) {
            LocalDate transactionDate = transaction.getDate();
            // isBefore and isAfter are boundary exclusive
            if (!transactionDate.isBefore(startDate) && !transactionDate.isAfter(endDate)) {
                // need to use negation to include start and end dates
                filteredTransactions.add(transaction);
            }
        }
        return filteredTransactions;
    }

    // Custom searching
}
