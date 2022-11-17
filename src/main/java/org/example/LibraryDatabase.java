package org.example;


import org.json.CDL;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibraryDatabase {
//    HashSet<Book> books = new HashSet<>(50);


public void csvToJson (){
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
}




    //Add all books to hashset books
    //Add books
    //Edit books
    //Delete books
    //Report of all books in library
    //Report of all books loaned out

