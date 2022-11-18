package org.example;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Users extends People{
    public int id;
    public String name;
    public boolean admin;

    public Users(int id, String name, boolean admin) {
        super(id, name, admin);
    }

    public Users() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void createUser(){
        JSONObject user = new JSONObject();
        Scanner name = new Scanner(System.in);
        System.out.println("Enter your name:");
        String firstName = name.next();
        user.put("First_Name", firstName);


        Scanner name1 = new Scanner(System.in);
        System.out.println("Enter your lastName:");
        String lastName = name1.next();
        user.put("Last_Name",lastName);


        try {
            FileWriter file = new FileWriter("user.json");
            file.write(user.toString());
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
