package org.example;

import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);
    LibraryDatabase db = new LibraryDatabase();
    Librarian librarian = new Librarian();
    Users user = new Users();
    public List<Librarian> allLibrians;
    public List<Users> allUsers;
    public Users loggedIn;

    public void welcomeMessage() throws IOException, CsvException {

        System.out.println("  _                     _____            __ _     _      _ _                          \n" +
                " | |                   / ____|          / _| |   | |    (_) |                         \n" +
                " | |     _____   _____| |     _ __ __ _| |_| |_  | |     _| |__  _ __ __ _ _ __ _   _ \n" +
                " | |    / _ \\ \\ / / _ \\ |    | '__/ _` |  _| __| | |    | | '_ \\| '__/ _` | '__| | | |\n" +
                " | |___| (_) \\ V /  __/ |____| | | (_| | | | |_  | |____| | |_) | | | (_| | |  | |_| |\n" +
                " |______\\___/ \\_/ \\___|\\_____|_|  \\__,_|_|  \\__| |______|_|_.__/|_|  \\__,_|_|   \\__, |\n" +
                "                                                                                 __/ |\n" +
                "                                                                                |___/");
        System.out.println("Welcome to Lovecraft's Library where you will find all Lovecraftian titles you can dream of.");
        logInScreen();
    }
    public void logInScreen() throws IOException, CsvException {
        System.out.println("Are you Admin or User?\n1: Admin\n2: User");
        String answer = scanner.next();
        if (answer.equalsIgnoreCase("1")){
            librarian.csvToJsonLibrarian();
            allLibrians = librarian.readJsonLibrarian();
            System.out.println("Please log in!\nID:");
            int id = scanner.nextInt();
            if (allLibrians.contains(allLibrians.get(id -1))){
                System.out.println("password:");
                String password = scanner.next();
                if (allLibrians.get(id -1).getPassword().contains(password)){
                    System.out.println("Access granted!");
                    librarianOptions();
                } else {
                    System.out.println("Incorrect Password, try again");
                    logInScreen();
                }
            } else {
                System.out.println("ID not recognised");
            }
        } else if (answer.equalsIgnoreCase("2")) {
            user.csvToJsonUsers();
            allUsers = user.readJsonUsers();
            System.out.println("Please log in!\nID:");
            int id = scanner.nextInt();
            if (allUsers.contains(allUsers.get(id -1))){
                System.out.println("password:");
                String password = scanner.next();
                if (allUsers.get(id -1).getPassword().contains(password)){
                    loggedIn = allUsers.get(id -1);
                    System.out.println("Access granted!");
                    userOptions();
                } else {
                    System.out.println("Incorrect Password, try again");
                    logInScreen();
                }
            } else {
                System.out.println("ID not recognised");
            }
            logInScreen();
        } else {
            System.out.println("Did not recognise input, try again");
            logInScreen();
        }
    }

    public void librarianOptions() throws IOException, CsvException {
        System.out.println("What would you like to do today?");
        System.out.println("(1) Create A User \n(2) Print All Users Report \n(3) Print Loaned Book Report \n(4) Print Available Book Report\n(5) See All Books\n(6) Exit");
        String choice = scanner.next();
        switch (choice){
            case "1":
                librarian.createUser();
                librarianOptions();
                break;
            case "2":
                librarian.seeAllUsers();
                librarianOptions();
            case "3":
                librarian.reportAllLoaned();
                librarianOptions();
            case "4":
                librarian.reportAllAvailable();
                librarianOptions();
            case "5":
                allBooks();
                librarianOptions();
            case "6":
                System.exit(0);
            default:
                System.out.println("Didn't understand input, try again");
                librarianOptions();
                break;
        }
    }
    public void allBooks(){
        db.csvToJson();
        db.readJson();
    }

    public void userOptions() throws IOException, CsvException {
        db.csvToJson();
        System.out.println("What would you like to do today?");
        System.out.println("(1) Lend A Book \n(2) Return A Book \n(3) See All Books\n(4) Exit");
        String choice = scanner.next();
        switch (choice){
            case "1":
                db.lendBook(loggedIn);
                userOptions();
                break;
            case "2":
                db.returnBook();
                userOptions();
            case "3":
                allBooks();
                userOptions();
            case "4":
                System.exit(0);
            default:
                System.out.println("Didn't understand input, try again");
                userOptions();
                break;
        }
    }
}
