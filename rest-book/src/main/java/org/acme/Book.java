package org.acme;

public class Book {

    public int id;
    public String title;
    public String author;
    public int yearOfPublication;
    public String genre;

    public Book(int id, String tittle, String author, int yearOfPublication, String genre) {
        this.id = id;
        this.title = tittle;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.genre = genre;
    }
}