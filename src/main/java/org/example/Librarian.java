package org.example;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.*;

public class Librarian {

    public int id;
    public String name;
    public String password;
    public boolean admin;
    private List<Librarian> allLibrians;
    private String[] newRow;

    public Librarian(int id, String name, String password, boolean admin) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.admin = admin;
    }

    public Librarian() {

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
    Scanner scanner = new Scanner(System.in);
    public void createUser() throws IOException, CsvException {
        System.out.println("Enter User ID: ");
        String id = scanner.next();
        System.out.println("Enter User Name: ");
        String name = scanner.next();
        System.out.println("Enter User Password: ");
        String password = scanner.next();
        System.out.println("Is this user admin? (true/false)");
        String admin = scanner.next();
        String user = id + "," + name + "," + password+ "," + admin;
        newRow = user.split(",");
        updateCSVLibrarian(newRow);

    }

    public void seeAllUsers(){
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

        Type listType = new TypeToken<List<Users>>() {
        }.getType();
        try {
            List<Users> users = new Gson().fromJson(new FileReader("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\users.json"), listType);
            System.out.println(users.toString().replace(",", ""));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void csvToJsonLibrarian() {
        File input = new File("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\src\\main\\resources\\librarians.csv");
        CsvSchema csv = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try {
            MappingIterator<Map<String, Librarian>> mappingIterator = csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<String, Librarian>> list = mappingIterator.readAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get("librarians.json").toFile(), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Librarian> readJsonLibrarian() {
        Type listType = new TypeToken<List<Librarian>>() {
        }.getType();
        try {
            List<Librarian> librarians = new Gson().fromJson(new FileReader("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\librarians.json"), listType);
            allLibrians = librarians;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allLibrians;
    }

    public void updateCSVLibrarian(String[] newRow) throws IOException, CsvException {
        String fileName = "C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\src\\main\\resources\\user.csv";
        CSVWriter writer = new CSVWriter(new FileWriter(fileName, true));
        writer.writeNext(newRow);
        writer.close();
    }

    public void reportAllLoaned(){
        ArrayList<Book> result = new ArrayList<Book>();
        Type listType = new TypeToken<List<Book>>() {
        }.getType();
        try {
            List<Book> books = new Gson().fromJson(new FileReader("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\books.json"), listType);
                for (Book book: books){
                    if (book.isLoaned()){
                        result.add(book);
                    }
                }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result.toString().replace(",", ""));
    }

    public void reportAllAvailable(){
        ArrayList<Book> result = new ArrayList<Book>();
        Type listType = new TypeToken<List<Book>>() {
        }.getType();
        try {
            List<Book> books = new Gson().fromJson(new FileReader("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\books.json"), listType);
            for (Book book: books){
                if (!book.isLoaned()){
                    result.add(book);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(result.toString().replace(",", ""));
    }
}
    //add librarian name/ id to book being loaned out

