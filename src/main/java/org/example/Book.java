package org.example;




public class Book {

    public int id;
    public String title;
    public String author;

    public boolean loaned;

    public Book(int id, String title, String author, boolean loaned) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.loaned = loaned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isLoaned() {
        return loaned;
    }

    public void setLoaned(boolean loaned) {
        this.loaned = loaned;
    }

    @Override
    public String toString() {
        return  id +": Title: "+ title+ " Author: " + author + " unavailable: " + loaned+ " \n" ;
    }
}
    //Loaning a book out (or book is loaned)

