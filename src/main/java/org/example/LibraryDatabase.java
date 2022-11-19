package org.example;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import org.json.CDL;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class LibraryDatabase {
    private List<Book> allBooks;

    Scanner scanner = new Scanner(System.in);

    public void csvToJson() {
        InputStream is = Book.class.getResourceAsStream("/books.csv");
        String csv = new BufferedReader(
                new InputStreamReader(Objects.requireNonNull(is), StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining("\n"));

        try {
            String json = CDL.toJSONArray(csv).toString(2);
            Files.write(Path.of("books.json"), json.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
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

        allBooks = readJson();
        System.out.println(allBooks.toString().replace(",", ""));
        System.out.println("What Book would you like to lend using Id?");
        int choice = scanner.nextInt();
        if(allBooks.contains(allBooks.get(choice - 1))){
            System.out.println(allBooks.get(choice -1));
            if (!allBooks.get(choice -1).isLoaned()){
                updateCSV(choice);

            } else {
                System.out.println("Sorry Not Available");
            }
        }


    }



    public void updateCSV( int editTerm) throws IOException, CsvException {
        String fileName = "C:\\Users\\Brook\\Documents\\puffin-nology\\Java\\lovecrafts-library\\src\\main\\resources\\books.csv";
        CSVReader reader = new CSVReader( new FileReader(fileName));
        List<String []> cvsBody = reader.readAll();
        cvsBody.get(editTerm)[3] = "true";
        CSVWriter writer = new CSVWriter(new FileWriter(fileName));
        writer.writeAll(cvsBody);
        writer.flush();

        csvToJson();
    }
}



    //Add all books to hashset books
    //Add books
    //Edit books
    //Delete books
    //Report of all books in library
    //Report of all books loaned out

