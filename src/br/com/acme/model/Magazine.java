/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.acme.model;

/**
 *
 * @author rodrigo.sonego
 */
public class Magazine extends Publication {
    private String issue;
    private int userId;

    public Magazine(String title, short year, String author, String issue) {
        super(title, year);
        this.setAuthor(author);
        this.issue = issue;
    }
    
    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
}
