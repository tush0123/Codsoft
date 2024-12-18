import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Method to deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    // Method to withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }

    // Method to check balance
    public double checkBalance() {
        return balance;
    }
}

// Class to represent the ATM
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    // Display the ATM menu
    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== ATM Menu ===");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter the amount to withdraw: ");
                    double amount = scanner.nextDouble();
                    account.withdraw(amount);
                }
                case 2 -> {
                    System.out.print("Enter the amount to deposit: ");
                    double amount = scanner.nextDouble();
                    account.deposit(amount);
                }
                case 3 -> {
                    System.out.println("Your current balance is: $" + account.checkBalance());
                }
                case 4 -> System.out.println("Thank you for using the ATM. Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        // Create a BankAccount with an initial balance
        BankAccount account = new BankAccount(1000.00);

        // Create an ATM connected to the bank account
        ATM atm = new ATM(account);

        // Display the ATM menu
        atm.displayMenu();
    }
}
