## Class Responsibilities

The application is organized into the following classes, each with a specific responsibility:

### Transaction
Represents an individual transaction and maintains its associated attributes.

### TransactionFileManager
Handles the saving and loading of transactions, managing how transaction data is stored and retrieved.

### Reports and Ledger
Handle how transactions are presented to the user, organizing transaction information into reports and ledger views.

## Refactoring Highlight: Eliminating Duplicate Display Methods

### Old Implementation

The original implementation used three separate methods, each containing nearly identical logic. Each method retrieved a collection of defined transactions, looped through the collection, and printed each transaction.

```java
public static void displayAll() {
    for (Transaction transaction : TransactionService.getAllTransactions()) {
        System.out.println(transaction);
    }
}

public static void displayDeposits() {
    for (Transaction transaction : TransactionService.getDeposits()) {
        System.out.println(transaction);
    }
}

public static void displayPayments() {
    for (Transaction transaction : TransactionService.getPayments()) {
        System.out.println(transaction);
    }
}
```

### Revised Implementation

I spotted the opportunity to eliminate this duplication by moving the responsibility for displaying transactions into a single method in the `Reports` class.

```java
public static void displayAll() {
    Reports.displayTransactions(TransactionService.getAllTransactions());
}

public static void displayDeposits() {
    Reports.displayTransactions(TransactionService.getDeposits());
}

public static void displayPayments() {
    Reports.displayTransactions(TransactionService.getPayments());
}
```

### Explanation: Separation of Responsibilities

Here is what I achieve in the revised implementation:

- **TransactionService** retrieves the appropriate transactions.
- **Ledger** determines which collection of transactions the user requested.
- **Reports** handles how that collection is displayed.

Instead of having three methods independently handle the printing logic, all three delegate that responsibility to `Reports.displayTransactions()`.

This eliminates duplicated code and makes the application easier to maintain. If the way transactions are displayed changes in the future, I only need to update one method.

I found this discovery to be a good example of **separation of responsibilities**: each class has a clearly defined role, and each method focuses on what it is responsible for doing.
