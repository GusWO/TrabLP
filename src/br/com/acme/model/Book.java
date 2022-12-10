package br.com.acme.model;

import java.io.Serializable;

public class Book extends Publication implements Serializable{

    private int idBook;
    private int FK_idUser;
    private String author;
    private String language;
    private String isbn;
    private int pages;
    
    public Book(String title, short year, String language, String isbn, int pages, String author){
        super(title,year);
        
        this.isbn = isbn;
        this.pages = pages;
        this.language = language;
        
        setAuthor(author);
    }
    
    public Book(int idBook, int FK_idUser, String title, short year, String author, String language, String isbn, int pages){
       super(title, year);
        
        this.idBook = idBook;
        this.FK_idUser = FK_idUser;
        this.author = author;
        this.language = language;
        this.isbn = isbn;
        this.pages = pages;
    } 
    
    public Book() {}

    public int getIdBook() {
        return idBook;
    }

    public int getFK_idUser() {
        return FK_idUser;
    }

    public void setFK_idUser(int FK_idUser) {
        this.FK_idUser = FK_idUser;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    
    
}
