package org.example;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
    LibraryDatabase db = new LibraryDatabase();
    db.csvToJson();
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("books.json"));
        Book data = gson.fromJson(reader, Book.class);
        System.out.println(data);

    }
}