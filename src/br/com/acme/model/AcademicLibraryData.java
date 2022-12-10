/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.acme.model;

import br.edu.unijui.xml.XMLCache;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class AcademicLibraryData implements Cloneable {

    public boolean isOfflineMode;
    public ArrayList<Article> articles;
    public ArrayList<Book> books;
    public ArrayList<User> users;

    public AcademicLibraryData copy() {
        try {
            return (AcademicLibraryData) this.clone();

        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(XMLCache.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
