package org.example;

import java.util.Scanner;

public class Display {
    Scanner scanner = new Scanner(System.in);


    public void welcomeMessage(){
        LibraryDatabase db = new LibraryDatabase();
        db.csvToJson();
        System.out.println("  _                     _____            __ _     _      _ _                          \n" +
                " | |                   / ____|          / _| |   | |    (_) |                         \n" +
                " | |     _____   _____| |     _ __ __ _| |_| |_  | |     _| |__  _ __ __ _ _ __ _   _ \n" +
                " | |    / _ \\ \\ / / _ \\ |    | '__/ _` |  _| __| | |    | | '_ \\| '__/ _` | '__| | | |\n" +
                " | |___| (_) \\ V /  __/ |____| | | (_| | | | |_  | |____| | |_) | | | (_| | |  | |_| |\n" +
                " |______\\___/ \\_/ \\___|\\_____|_|  \\__,_|_|  \\__| |______|_|_.__/|_|  \\__,_|_|   \\__, |\n" +
                "                                                                                 __/ |\n" +
                "                                                                                |___/");
        System.out.println("Welcome to Lovecraft's Library where you will find all Lovecraftian titles you can dream of.\nWould you like to see all the books?");
        String answer = scanner.next();
        if(answer.equalsIgnoreCase("yes")){
            db.readJson();
        } else if (answer.equalsIgnoreCase("no")) {
            System.out.println("Have a lovely day!");
        }else {
            System.out.println("Sorry don't recognise that input");
        }

    }
}
