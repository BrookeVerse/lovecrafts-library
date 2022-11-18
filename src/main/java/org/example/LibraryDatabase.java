package org.example;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.json.CDL;

import javax.xml.crypto.Data;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public void lendBook() {
        allBooks = readJson();
        System.out.println(allBooks.toString().replace(",", ""));
        System.out.println("What Book would you like to lend using Id?");
        int choice = scanner.nextInt();
        if(allBooks.contains(allBooks.get(choice - 1))){
            System.out.println(allBooks.get(choice -1));
            if (!allBooks.get(choice -1).isLoaned()){
                allBooks.get(choice -1).setLoaned(true);
                updateCSV(String.valueOf(choice), "true");
                System.out.println(allBooks.toString().replace(",", ""));
            } else {
                System.out.println("Sorry Not Available");
            }
        }


    }

    String fileName = "/books.csv";

    public void updateCSV( String editTerm, String newLoaned){


       String tempFile = "/temp.csv";
       File oldFile = new File(fileName);
       File newFile = new File(tempFile);
       String id = ""; String name = ""; String author = ""; String loaned = "";
        try{
           FileWriter fw = new FileWriter(tempFile, true);
           BufferedWriter bw = new BufferedWriter(fw);
           PrintWriter pw = new PrintWriter(bw);
            Scanner x = new Scanner(new File(fileName));
           x.useDelimiter("[,\n]");

           while (x.hasNext()){
               id = x.next();
               name = x.next();
               author = x.next();
               loaned = x.next();
               if (id.equals(editTerm)){
                   pw.println(id + "," + name + "," + name + "," + newLoaned);
               } else {
                   pw.println(id + "," + name + "," + author + "," + loaned);
               }
           }
           x.close();
           pw.flush();
           pw.close();
           oldFile.delete();
           File dump = new File(fileName);
           newFile.renameTo(dump);
        }catch (Exception e){
            System.out.println("Error");
        }
    }
}



    //Add all books to hashset books
    //Add books
    //Edit books
    //Delete books
    //Report of all books in library
    //Report of all books loaned out

