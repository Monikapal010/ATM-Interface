import java.util.*;

class ATM {
    @SuppressWarnings("FieldMayBeFinal")
    private String userId;
    @SuppressWarnings("FieldMayBeFinal")
    private String userPin;
    private double balance;
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> transactionHistory;

    public ATM(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String enteredId, String enteredPin) {
        return userId.equals(enteredId) && userPin.equals(enteredPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: " + amount);
            System.out.println("✅ Successfully deposited: " + amount);
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("✅ Successfully withdrew: " + amount);
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: " + balance);
    }

    public void showTransactionHistory() {
        System.out.println("📜 Transaction History:");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String txn : transactionHistory) {
                System.out.println(txn);
            }
        }
    }
}

public class ATMInterface {
    public static void main(String[] args) {
        // demo ATM account (userId: "user123", pin: "1234", balance: 5000)
        try (Scanner sc = new Scanner(System.in)) {
            // demo ATM account (userId: "user123", pin: "1234", balance: 5000)
            ATM atm = new ATM("user123", "1234", 5000);
            
            System.out.println("===== Welcome to ATM Machine =====");
            System.out.print("Enter User ID: ");
            String enteredId = sc.nextLine();
            System.out.print("Enter PIN: ");
            String enteredPin = sc.nextLine();
            
            if (atm.authenticate(enteredId, enteredPin)) {
                System.out.println("✅ Authentication Successful!");
                int choice;
                do {
                    System.out.println("\n===== ATM Menu =====");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Transaction History");
                    System.out.println("5. Exit");
                    System.out.print("Enter your choice: ");
                    choice = sc.nextInt();
                    
                    switch (choice) {
                        case 1 -> atm.checkBalance();
                        case 2 -> {
                            System.out.print("Enter deposit amount: ");
                            double dep = sc.nextDouble();
                            atm.deposit(dep);
                        }
                        case 3 -> {
                            System.out.print("Enter withdraw amount: ");
                            double with = sc.nextDouble();
                            atm.withdraw(with);
                        }
                        case 4 -> atm.showTransactionHistory();
                        case 5 -> System.out.println("👋 Thank you for using the ATM. Goodbye!");
                        default -> System.out.println("❌ Invalid choice. Try again.");
                    }
                } while (choice != 5);
                
            } else {
                System.out.println("❌ Authentication Failed. Please try again!");
            }
        }
    }
}
