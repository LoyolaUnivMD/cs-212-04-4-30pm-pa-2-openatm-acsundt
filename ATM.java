//pa1

import java.util.Scanner;
import java.util.Objects;

public class ATM { //remember to change the name of the class to match the name of the file.

    public String[][] availableAccounts = new String[5][];
    public String[] accountInfo;
    int recentTransactionAmount;
    double recentTransactions[];
    public String name;
    public double intialDeposit;
    public int accountNumber;
    public int counter = 0;

    public ATM(int recentTransactionAmount, String name, double intialDeposit, int accountNumber){
        this.name = name;
        this.accountInfo = new String[]{name, String.valueOf(accountNumber), String.valueOf(intialDeposit)};
        this.recentTransactions = new double[recentTransactionAmount];
        this.accountNumber = accountNumber;
//        availableAccounts[counter] = accountInfo; counter++;
    }

    public boolean isOwner(String name){
        for(int i = 0; i < 5 ; i++){
            if (Objects.equals(availableAccounts[i][0], name)){
                return true;
            }
        }
        return false;

    }

    public void deposit(ATM user){
        Scanner in = new Scanner(System.in);
        System.out.println("How much would you like to deposit?");
        double depositAmount = in.nextDouble();
        while (depositAmount < 0){
            System.out.println("You cannot deposit a negative amount. Please enter new deposit amount");
            depositAmount = in.nextDouble();
        }
        double temp = depositAmount + Double.parseDouble(user.accountInfo[2]);
        user.accountInfo[2] = String.valueOf(temp);

    }

    public void withdraw(ATM user) {
        Scanner in = new Scanner(System.in);

        double withdrawAmount = 0;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter a number: ");
            String userInput = in.nextLine();

            try {
                withdrawAmount = Double.parseDouble(userInput);
                while (withdrawAmount <= 0 || withdrawAmount > Double.parseDouble(user.accountInfo[2])) {
                    if (withdrawAmount <= 0 || withdrawAmount > Double.parseDouble(user.accountInfo[2])) {
                        System.out.println("You cannot withdraw zero or less or withdraw more than you have currently in your account. Please enter new deposit amount");
                        withdrawAmount = in.nextDouble();
                    }
                }
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        double temp = withdrawAmount + Double.parseDouble(user.accountInfo[2]);
        user.accountInfo[2] = String.valueOf(temp);
    }





    public static void main(String[] args){







    } // end of main method

} // end of ATM class