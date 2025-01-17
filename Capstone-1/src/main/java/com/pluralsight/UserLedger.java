package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.AccountingLedgerAppMain.homeScreen;
import static com.pluralsight.AccountingLedgerAppMain.returnHomeprompt;

public class UserLedger {
    LocalDate date;
    LocalTime time;
    String vendor;
    String itemDescription;
    Double amountChanged;

    public UserLedger(String date, String time, String itemDescription, String vendor, Double amountChanged) {
        this.amountChanged = amountChanged;
        this.itemDescription = itemDescription;
        this.vendor = vendor;
        this.time = LocalTime.parse(String.valueOf(time));
        this.date = LocalDate.parse(String.valueOf(date));
    }
static Scanner scanner = new Scanner(System.in);
    public static void ledgerMenu() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("\n~LEDGER MENU~");
        System.out.println("What would you like to do with your ledger today? Please enter command for desired option:\n\n" +
                "A) All - Display all entries\n" +
                "D) Deposits - Display all deposits\n" +
                "P) Payments - Display all payments\n" +
                "R) Reports - Display reports menu\n" +
                "H) Home - Return to main menu\n\n" +
                "Enter Command:");

        String menuChoice = scanner.nextLine();
        switch (menuChoice.toUpperCase()) {
            case "A":
                viewLedger();
                ledgerLoop();
                break;
            case "D":
                viewDeposits();
                ledgerLoop();
                break;
            case "P":
                viewPayments();
                ledgerLoop();
                break;
            case "R":
                Reports.reportsMenu();
                break;
            case "H":
                homeScreen();
                break;
            default:
                System.out.println("Invalid Input");
                ledgerMenu();
        }
    }

    public static void ledgerLoop() throws InterruptedException {

            System.out.println("Would you like to go back to the ledger page? Please enter enter y/n");
            String reportsPage = scanner.nextLine().toLowerCase();
            switch (reportsPage){
                case "y":
                    ledgerMenu();
                    break;
                case "n":
                    homeScreen();
                default:
                    ledgerLoop();
            }
    }

    public static void viewLedger() throws InterruptedException {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~CURRENT UP-TO-DATE LEDGER~");
            System.out.println("Here is your current account ledger as of: " + LocalDate.now() + "\n");
            FileInputStream transactions = new FileInputStream("transactions2.csv");
            Scanner scanner = new Scanner(transactions);

            String input;

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                List<String> ledger = List.of(input);
                for (int i = 0; i < ledger.size(); i++) {
                    System.out.println(ledger.get(i));
                }
            }
            System.out.println("joemamas");
            ledgerLoop();
            scanner.close();

        } catch (IOException | InterruptedException e) {
            System.out.println("Invalid Input");
            throw new RuntimeException(e);
        }
    }

    public static void getUser() {
        System.out.println("\n~USER LOGIN~");
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);


        System.out.println("\n\nWELCOME " + name.toUpperCase() + "\n\n\n\nUser Entry Logged At: " + formattedDateTime +"\n\n\n");
        System.out.println("Loading your information...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeDeposit() {
        try {
            System.out.println("\n~NEW DEPOSIT~");

            FileWriter fileWriter = new FileWriter("transactions2.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of your deposit: ");
            double deposit = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the deposit: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the deposit: ");
            String vendor = scanner.nextLine();

            UserLedger paymentLedger = new UserLedger(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), description, vendor, deposit);
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + deposit;
            System.out.println("Processing information...");
            Thread.sleep(2000);
            System.out.println("\n\nYour deposit of " + deposit + " for item " + description + "by " + vendor + " has been successfuly added to your account ledger on: " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();
            askForAnotherDeposit();

        } catch (IOException | InterruptedException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public static void askForAnotherDeposit () throws InterruptedException {
        System.out.println("Would You Like To Make Another Deposit? (Y/N)");
        String userChoice = scanner.nextLine();
        if (userChoice.equalsIgnoreCase("Y")) {
            makeDeposit();
        }
        if (userChoice.equalsIgnoreCase("N")) {
            ledgerMenu();
        }
    }

    public static void makePayment() {
        try {

            System.out.println("\n~NEW PAYMENT~");

            FileWriter fileWriter = new FileWriter("transactions2.csv",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            System.out.println("Please enter the amount of your payment: ");
            double payment = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Please enter a description of the payment: ");
            String description = scanner.nextLine();

            System.out.println("Please enter the vendor name for the payment: ");
            String vendor = scanner.nextLine();

            UserLedger paymentLedger = new UserLedger(String.valueOf(LocalDate.now()), String.valueOf(LocalTime.now()), description, vendor, payment);
            String adjustedLedger = "\n" + paymentLedger.getDate() + "|" + paymentLedger.getTime() + "|" + description + "|" + vendor + "|" + "-" + payment;
            System.out.println("Processing information...");
            Thread.sleep(2000);
            System.out.println("\n\nYour payment of " + payment + " for item " + description + " by " + vendor + " has been successfuly added to your account ledger on: " + paymentLedger.getDate() + " " + paymentLedger.getTime());
            bufferedWriter.write(adjustedLedger);

            bufferedWriter.close();

        } catch (IOException | InterruptedException e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public static void viewDeposits() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~DEPOSIT REPORTS~");
            System.out.println("Here are all of your current deposit transactions as of " + LocalDate.now() + "\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double. parseDouble(arrTransactions[4]));
                double deposit = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String vendor = arrTransactions[3];

                if (f.getAmountChanged() > 0) {
                    System.out.println(f.getDate() + "|" + f.getTime() + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                }
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }

    public static void viewPayments() {
        try {
            System.out.println("Loading your information...");
            Thread.sleep(2000);
            System.out.println("\n~PAYMENT REPORT");
            System.out.println("Here are all of your current payment transactions as of " + LocalDate.now() + "\n");

            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input = bufReader.readLine();
            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                while (f.getAmountChanged() < 0) {
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    break;
                }
                System.out.println("You have no transactions for the current month");
            }

        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
        }
    }


    public String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(dtf);
        return formattedDate;
    }

    public String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = time.format(dtf);
        return formattedTime;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getAmountChanged() {
        return amountChanged;
    }

    public void setAmountChanged(Double amountChanged) {
        this.amountChanged = amountChanged;
    }

    public void reports() {
        System.out.println("");
        return;

    }
    public void deposit() {
        System.out.println("What is your");

    }
}