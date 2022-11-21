package org.example;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Users{
    public int id;
    public String name;
    public String password;
    public Book booksBorrowed;
    public List<Book> listBooks;
    public boolean admin;
    private List<Users> allUsers;

    public Users(int id, String name, String password, boolean admin, Book booksBorrowed) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
        this.booksBorrowed = booksBorrowed;
    }

    public Users() {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Book getBooksBorrowed() {
        return booksBorrowed;
    }

    public void setBooksBorrowed(Book booksBorrowed) {
        this.booksBorrowed = booksBorrowed;
    }

    @Override
    public String toString() {
        return  id + ": name: " + name + " admin:" + admin + " \n";
    }


    public void csvToJsonUsers() {
        File input = new File("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\src\\main\\resources\\user.csv");
        CsvSchema csv = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try {
            MappingIterator<Map<String, Users>> mappingIterator = csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<String, Users>> list = mappingIterator.readAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get("users.json").toFile(), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Users> readJsonUsers() {
        Type listType = new TypeToken<List<Users>>() {
        }.getType();
        try {
            allUsers = new Gson().fromJson(new FileReader("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\users.json"), listType);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }
}

