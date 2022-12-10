package br.com.acme.model;

import java.io.Serializable;

public abstract class Publication implements Serializable{

    private String title;
    private short year;
    private String author;

    public Publication(String title, short year) {
        this.title = title;
        this.year = year;
    }
    
    public Publication () {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }
    
    public void setAuthor(String author){
        this.author = author;
    }

    
    public String getAuthor(){
        return author;
    }

}
