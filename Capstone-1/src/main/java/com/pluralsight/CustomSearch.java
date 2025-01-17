package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CustomSearch {
    public static Scanner scanner = new Scanner(System.in);

    public static void customSearch() throws InterruptedException {
        try {
            Thread.sleep(500);
            System.out.println("\n~CUSTOM SEARCH~");
            System.out.println("Please enter vendor name, amount of deposit or payment, item description, or time period (i.e start date - end date) for your search: ");

            String menuChoice = scanner.nextLine();
            FileReader fileReader = new FileReader("transactions2.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input = bufReader.readLine();

            while ((input = bufReader.readLine()) != null) {
                String[] arrTransactions = input.split("\\|");
                LocalDate ledgerDate = LocalDate.parse(arrTransactions[0]);
                double amount = Double.parseDouble(arrTransactions[4]);
                String description = arrTransactions[2];
                String[] descriptionSplit = description.split(" ");
                String[] timePeriod = menuChoice.split("-");
                LocalDate startDate = LocalDate.parse(timePeriod[0]);
                LocalDate endDate = LocalDate.parse(timePeriod[1]);
                ArrayList<String> descriptionArr = new ArrayList<>();
//            for (int i = 0; i < descriptionSplit.length; i++ ) {
//                System.out.println(i);
//            }
                String vendor = arrTransactions[3];
                UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                String date = f.getDate();

                if (menuChoice.equalsIgnoreCase(vendor)) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (menuChoice.equalsIgnoreCase((arrTransactions[2]))) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (menuChoice.equalsIgnoreCase(Arrays.toString(descriptionSplit))) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (menuChoice.equals(String.valueOf(amount))) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (ledgerDate.isAfter(startDate)) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (ledgerDate.isBefore(endDate)) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                } if (menuChoice.equals(amount)) {
                    System.out.println("\nTransactions found under " + "'" + menuChoice + "'" + ":\n");
                    System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("Error retrieving data");
            throw new RuntimeException(e);
        }
       AccountingLedgerAppMain.returnHomeprompt();
    }
}
