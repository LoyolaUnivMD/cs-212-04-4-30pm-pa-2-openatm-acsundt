import java.util.Scanner;

public class Account {
    //declare data types
    private String ownerName;
    private double balance;
    private double[] recentTransactions;
    private int transactionCount;

    // Constructor
    public Account(String ownerName, double initialDeposit) {
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.recentTransactions = new double[5];
        this.transactionCount = 0;
    }

    // Check if the account is owned by the name
    public boolean isOwner(String name) {
        return this.ownerName.equals(name);
    }

    // Deposit money into the account
    public void deposit() {

        Scanner in = new Scanner(System.in);
        double depositAmount=0;


        boolean validInput = false;

        while (!validInput) {

            try {
                System.out.print("Enter a deposit amount: ");
                String amount = in.nextLine();
                depositAmount = Double.parseDouble(amount);
                if (depositAmount <= 0) {
                    System.out.println("You cannot deposit zero or less. Please enter new deposit amount.");
                }
                if (depositAmount > 0) {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }

        }
        this.balance += depositAmount;
        recordTransaction(depositAmount);
    }

    // Withdraw money from the account
    public void withdraw() {
        Scanner in = new Scanner(System.in);
        double withdrawAmount=0;


        boolean validInput = false;

        while (!validInput) {

            try {
                System.out.print("Enter a withdraw amount: ");
                String amount = in.nextLine();
                withdrawAmount = Double.parseDouble(amount);
                if (withdrawAmount <= 0 || withdrawAmount > balance) {
                    System.out.println("You cannot withdraw zero or less or withdraw more than you have currently in your account. Please enter new withdrawal amount.");
                }
                if (withdrawAmount >= 0 && withdrawAmount < balance) {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }

        }
        this.balance -= withdrawAmount;
        recordTransaction(-withdrawAmount);
    }

    // Record transaction in the recentTransactions array
    private void recordTransaction(double amount) {
        if (transactionCount < recentTransactions.length) {
            recentTransactions[transactionCount] = amount;
            transactionCount++;
        } else {
            // Shift the transactions to make space for the new one
            for (int i = 0; i < recentTransactions.length - 1; i++) {
                recentTransactions[i] = recentTransactions[i + 1];
            }
            recentTransactions[recentTransactions.length - 1] = amount;
        }
    }

    // Calculate statistics of recent transactions
    public double[] getStats() {
        double min = 9999999;
        double max = 0;
        double total = 0;

        for (double transaction : recentTransactions) {
            total += transaction;
            if (transaction < min) {
                min = transaction;
            }
            if (transaction > max) {
                max = transaction;
            }
        }

        double average = total / transactionCount;
        return new double[]{min, max, average};
    }

    // Display account information
    public void display() {
        System.out.println("Account Owner: " + ownerName);
        System.out.println("Account Balance: $" + balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account[] accounts = new Account[5]; // Array to store accounts
        int accountCount = 0; // Number of created accounts

        String name = "";
        while (!name.equalsIgnoreCase("exit")) {
            System.out.println("Enter your first and last name (or 'exit' to quit): ");
            name = scanner.nextLine();
            if (name.equalsIgnoreCase("exit")) {
                continue;
            }

            Account currentAccount = null;

            // Check if the user already has an account
            for (int i = 0; i < accountCount; i++) {
                if (accounts[i].isOwner(name)) {
                    currentAccount = accounts[i];
                    break;
                }
            }

            if (currentAccount == null) {
                if (accountCount < 5) {
                    System.out.println("Creating a new account for " + name);
                    System.out.println("Enter initial deposit amount: ");
                    double initialDeposit = Double.parseDouble(scanner.nextLine());
                    currentAccount = new Account(name, initialDeposit);
                    accounts[accountCount] = currentAccount;
                    accountCount++;
                } else {
                    //restart the while loop
                    System.out.println("Sorry, all accounts are already in use.");
                    continue;
                }
            }

            // Display info
            currentAccount.display();
            System.out.println("Your account number is: " + currentAccount.hashCode());
            int option = 0;

            // Menu options
            while (option != 5) {
                System.out.println("Select an option:");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Get Statistics");
                System.out.println("4. View Recent Transactions");
                System.out.println("5. Exit");

                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        currentAccount.deposit();
                        break;
                    case 2:

                        currentAccount.withdraw();
                        break;
                    case 3:
                        double[] stats = currentAccount.getStats();
                        System.out.println("Minimum Transaction: $" + stats[0]);
                        System.out.println("Maximum Transaction: $" + stats[1]);
                        System.out.println("Average Transaction: $" + stats[2]);
                        break;
                    case 4:
                        System.out.println("Recent Transactions:");
                        for (double transaction : currentAccount.recentTransactions) {
                            System.out.println("$" + transaction);
                        }
                        break;
                    case 5:
                        System.out.println("Thank you for using the ATM!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        }
    }
}