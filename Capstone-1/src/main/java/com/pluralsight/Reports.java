package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Reports {
    public static Scanner scanner = new Scanner(System.in);

    public static void reportsMenu () throws InterruptedException {
        try {
            Thread.sleep(500);
            System.out.println("\n~LEDGER REPORTS~");
            System.out.println("Which report would you like to view? Please enter command corresponding to your desired type of report:\n\n" +
                    "1) Month to Date\n" +
                    "2) Previous Month\n" +
                    "3) Year to Date\n" +
                    "4) Previous Year\n" +
                    "5) By Vendor\n" +
                    "6) Custom Search\n" +
                    "0) Go Back to Ledgers Menu\n" +
                    "H) Go to Home Screen\n\n" +
                    "Enter Command:");

            String menuChoice = scanner.nextLine();

            switch (menuChoice.toUpperCase()) {
                case "1":
                    mtdReport();

                    break;
                case "2":
                    previousMonthReport();

                    break;
                case "3":
                    yearToDateReport();

                    break;
                case "4":
                    previousYearReport();

                    break;
                case "5":
                    vendorReport();

                    break;
                case "6":
                    CustomSearch.customSearch();
                    break;
                case "0":
                    UserLedger.ledgerMenu();
                    break;
                case "H":
                   AccountingLedgerAppMain.homeScreen();
                    break;
                default:
                    System.out.println("Invalid Input");
                    reportsMenu();
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");
            e.printStackTrace();
            AccountingLedgerAppMain.returnHomeprompt();
        }
    }
        public static void mtdReport() {
            try {
                System.out.println("Loading your information...");
                Thread.sleep(2000);
                System.out.println("\n~MONTH TO DATE REPORT~");
                System.out.println("Here are all of your current month to date transactions as of " + LocalDate.now() + ":\n");

                FileReader fileReader = new FileReader("transactions2.csv");
                BufferedReader bufReader = new BufferedReader(fileReader);

                String input = bufReader.readLine();
                while ((input = bufReader.readLine()) != null) {
                    String[] arrTransactions = input.split("\\|");

                    UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                    double now = LocalDate.now().getMonthValue();
                    String[] getTransactionMonth = arrTransactions[0].split("-");
                    double transactionMonth = Double.parseDouble(getTransactionMonth[1]);

                    if (transactionMonth != now) {
                        System.out.println(" ");
                        continue;
                    } if (transactionMonth == now) {
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } else {
                        System.out.println("No transactions found");
                    }
                }
                bufReader.close();
                AccountingLedgerAppMain.returnHomeprompt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                e.printStackTrace();
            }
        }

        public static void vendorReport() {
            try {
                System.out.println("\n~SEARCH BY VENDOR REPORT~");
                System.out.println("Enter the vendor name of the transactions you would like to view: ");

                String vendorName = scanner.nextLine();
                System.out.println("Loading your information...");
                Thread.sleep(2000);

                FileReader fileReader = new FileReader("transactions2.csv");
                BufferedReader bufReader = new BufferedReader(fileReader);

                String input = bufReader.readLine();
                while ((input = bufReader.readLine()) != null) {
                    String[] arrTransactions = input.split("\\|");
                    UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                    String findName = arrTransactions[3];

                    if (vendorName.equalsIgnoreCase(findName)) {
                        System.out.println("\nTransactions found under " + vendorName + ":\n");
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } if (vendorName != findName) {
                        System.out.println("You have no transactions under '" + vendorName + "'");
                        break;
                    }
                }
                AccountingLedgerAppMain.returnHomeprompt();
            } catch (IOException e) {
                System.out.println("Invalid Input");
                e.printStackTrace();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        public static void previousMonthReport() throws InterruptedException {
            try {
                System.out.println("Loading your information...");
                Thread.sleep(2000);
                System.out.println("\n~PREVIOUS MONTH REPORT~");
                System.out.println("Here are all of your last month's' transactions as of " + LocalDate.now() + ":\n");

                FileReader fileReader = new FileReader("transactions2.csv");
                BufferedReader bufReader = new BufferedReader(fileReader);

                String input = bufReader.readLine();
                while ((input = bufReader.readLine()) != null) {
                    String[] arrTransactions = input.split("\\|");
                    UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                    double now = LocalDate.now().getMonthValue() - 1;
                    String[] getTransactionMonth = arrTransactions[0].split("-");
                    double transactionMonth = Double.parseDouble(getTransactionMonth[1]);

                    if (transactionMonth != now){
                        System.out.println(" ");
                    } if (transactionMonth == now) {
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } else {
                        System.out.println("No transactions found");
                    }
                }
                bufReader.close();
                AccountingLedgerAppMain.returnHomeprompt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                e.printStackTrace();
            }
        }

        public static void yearToDateReport() {
            try {
                System.out.println("Loading your information...");
                Thread.sleep(2000);
                System.out.println("\n~YEAR TO DATE REPORT~");
                System.out.println("Here are all of your current year to date transactions as of " + LocalDate.now() + ":\n");

                FileReader fileReader = new FileReader("transactions2.csv");
                BufferedReader bufReader = new BufferedReader(fileReader);

                String input = bufReader.readLine();
                while ((input = bufReader.readLine()) != null) {
                    String[] arrTransactions = input.split("\\|");
                    UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                    double now = LocalDate.now().getYear();
                    String[] getTransactionMonth = arrTransactions[0].split("-");
                    double transactionYear = Double.parseDouble(getTransactionMonth[0]);

                    if (transactionYear != now){
                        System.out.println(" ");
                    } if (transactionYear == now) {
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } else {
                        System.out.println("No transactions found");
                    }
                }
                bufReader.close();
                AccountingLedgerAppMain.returnHomeprompt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                e.printStackTrace();
            }
        }

        public static void previousYearReport() {
            try {
                System.out.println("Loading your information...");
                Thread.sleep(2000);
                System.out.println("\n~MONTH TO DATE REPORT~");
                System.out.println("Here are all of your current month to date transactions as of " + LocalDate.now() + ":\n");

                FileReader fileReader = new FileReader("transactions2.csv");
                BufferedReader bufReader = new BufferedReader(fileReader);

                String input = bufReader.readLine();
                while ((input = bufReader.readLine()) != null) {
                    String[] arrTransactions = input.split("\\|");
                    UserLedger f = new UserLedger(arrTransactions[0], arrTransactions[1], arrTransactions[2], arrTransactions[3], Double.parseDouble(arrTransactions[4]));
                    double now = LocalDate.now().getYear() - 1;
                    String[] getTransactionMonth = arrTransactions[0].split("-");
                    double transactionYear = Double.parseDouble(getTransactionMonth[0]);

                    if (transactionYear != now){
                        System.out.println(" ");
                    } if (transactionYear == now) {
                        System.out.println(f.date + "|" + f.time + "|" + f.itemDescription + "|" + f.vendor + "|" + f.amountChanged);
                    } else {
                        System.out.println("No transactions found");
                    }
                }
                bufReader.close();
                AccountingLedgerAppMain.returnHomeprompt();
            } catch (Exception e) {
                System.out.println("Invalid Input");
                e.printStackTrace();
            }
        }

    }
