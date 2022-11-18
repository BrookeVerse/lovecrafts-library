package org.example;

import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);


    public void welcomeMessage(){

        System.out.println("  _                     _____            __ _     _      _ _                          \n" +
                " | |                   / ____|          / _| |   | |    (_) |                         \n" +
                " | |     _____   _____| |     _ __ __ _| |_| |_  | |     _| |__  _ __ __ _ _ __ _   _ \n" +
                " | |    / _ \\ \\ / / _ \\ |    | '__/ _` |  _| __| | |    | | '_ \\| '__/ _` | '__| | | |\n" +
                " | |___| (_) \\ V /  __/ |____| | | (_| | | | |_  | |____| | |_) | | | (_| | |  | |_| |\n" +
                " |______\\___/ \\_/ \\___|\\_____|_|  \\__,_|_|  \\__| |______|_|_.__/|_|  \\__,_|_|   \\__, |\n" +
                "                                                                                 __/ |\n" +
                "                                                                                |___/");
        System.out.println("Welcome to Lovecraft's Library where you will find all Lovecraftian titles you can dream of.");
        userOptions();
    }
    public void allBooks(){
        LibraryDatabase db = new LibraryDatabase();
        db.csvToJson();
        db.readJson();
    }

    public void userOptions(){
        LibraryDatabase db = new LibraryDatabase();
        System.out.println("What would you like to do today?");
        System.out.println("(1) Lend A Book \n(2) Return A Book \n(3) See All Books\n(4) Exit");
        String choice = scanner.next();
        switch (choice){
            case "1":
                db.lendBook();
                userOptions();
                break;
            case "2":
                System.out.println("You put it back");
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
