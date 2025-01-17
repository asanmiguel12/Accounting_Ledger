package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.pluralsight.UserLedger.*;


public class AccountingLedgerAppMain {
    static Scanner scanner = new Scanner(System.in);
    public void main () {
        homeScreen();
        scanner.close();
    }
    public static void homeScreen() {
        try {
            System.out.println("\n~WELCOME TO YOUR ACCOUNT LEDGER APPLICATION!~\n\n" +
                    "What would you like to do today? Please enter command for desired option:\n\n" +
                    "(L) View Ledger\n" +
                    "(D) Make Deposit\n" +
                    "(P) Make Payment\n" +
                    "(X) Exit\n\n" +
                    "Enter Command:");

            String menuChoice = scanner.nextLine();
            switch (menuChoice.toUpperCase()) {
                case "L":
                    ledgerMenu();
                    break;
                case "D":
                    makeDeposit();
                    break;
                case "P":
                    UserLedger.makePayment();
                    break;
                case "X":
                    exit();
                    break;
                default:
                    Thread.sleep(800);
                    System.out.println("Invalid Input");
                    homeScreen();
            }
        } catch (Exception e) {
            System.out.println("Input Error");
            e.printStackTrace();
        }
    }


    public static void returnHomeprompt() throws InterruptedException {
        System.out.println("\nWould you like to do run a new report?\n" +
                "Y) Yes\n" +
                "N) No\n\n" +
                "Enter Command: ");
        String menuChoice = scanner.nextLine();

        switch (menuChoice.toLowerCase()){
            case "y":
                Reports.reportsMenu();
            case "n":
                ledgerMenu();
            default:
                returnHomeprompt();
        }
    }

    public static void exit() {
        System.out.println("User Entry Logged Out At: " + LocalDateTime.now());
        System.out.println("\nThank You For Using Our Account Ledger Application!" +
                "\nHave A Nice Day!");
        System.exit(0);
    }
}
