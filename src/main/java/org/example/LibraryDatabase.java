package org.example;


import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LibraryDatabase {
    private List<Book> allBooks;

    Scanner scanner = new Scanner(System.in);
    public void csvToJson() {
        File input = new File("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\src\\main\\resources\\books.csv");
        CsvSchema csv = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        try {
            MappingIterator<Map<String, Book>>mappingIterator = csvMapper.reader().forType(Map.class).with(csv).readValues(input);
            List<Map<String, Book>> list = mappingIterator.readAll();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get("books.json").toFile(), list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Book> readJson() {
        Type listType = new TypeToken<List<Book>>() {
        }.getType();
        try {
            List<Book> books = new Gson().fromJson(new FileReader("C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\books.json"), listType);
            System.out.println(books.toString().replace(",", ""));
            allBooks = books;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allBooks;
    }

    public void lendBook() throws IOException, CsvException {
        String loaned = "true";
        allBooks = readJson();
        System.out.println(allBooks.toString().replace(",", ""));
        System.out.println("What Book would you like to lend using Id?");
        int choice = scanner.nextInt();
        if(allBooks.contains(allBooks.get(choice - 1))){
            System.out.println(allBooks.get(choice -1));
            if (!allBooks.get(choice -1).isLoaned()){
                updateCSV(choice, loaned);
            } else {
                System.out.println("Sorry Not Available");
            }
        }
    }

    public void returnBook() throws IOException, CsvException {
        String loaned = "false";
        allBooks = readJson();
        System.out.println(allBooks.toString().replace(",", ""));
        System.out.println("What Book would you like to return using Id?");
        int choice = scanner.nextInt();
        if(allBooks.contains(allBooks.get(choice - 1))){
            System.out.println(allBooks.get(choice -1));
            if (allBooks.get(choice -1).isLoaned()){
                updateCSV(choice, loaned);
            } else {
                System.out.println("Sorry that book is not loaned");
            }
        }
    }



    public void updateCSV( int editTerm, String loaned) throws IOException, CsvException {
        String fileName = "C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\src\\main\\resources\\books.csv";
        CSVReader reader = new CSVReader( new FileReader(fileName));
        List<String []> cvsBody = reader.readAll();
        cvsBody.get(editTerm)[3] = loaned;
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        writer.writeAll(cvsBody);
        writer.flush();
        System.out.println("You have lent " + Arrays.toString(cvsBody.get(editTerm)) +"\nPlease bring it back in two weeks.");
    }
}



    //Add all books to hashset books
    //Add books
    //Edit books
    //Delete books
    //Report of all books in library
    //Report of all books loaned out

