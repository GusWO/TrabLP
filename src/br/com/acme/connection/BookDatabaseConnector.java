/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.acme.connection;

import br.com.acme.model.Book;
import br.com.acme.model.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDatabaseConnector {

    public static boolean InsertBook(Book book) {
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ptstm = null;
        String insertBookSql = "INSERT INTO book(FK_idUser, title, year, author, language, isbn, pages) "
                + " VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        int userId = Session.getInstance().getLoggedUser().getIdUser();
        
        String updateCountSql = "UPDATE user SET publication_limit = publication_limit - 1 WHERE idUser =  ?";

        try {
            con.setAutoCommit(false);
            
            ptstm = con.prepareStatement(insertBookSql);

            ptstm.setInt(1, book.getFK_idUser());
            ptstm.setString(2, book.getTitle());
            ptstm.setInt(3, book.getYear());
            ptstm.setString(4, book.getAuthor());
            ptstm.setString(5, book.getLanguage());
            ptstm.setString(6, book.getIsbn());
            ptstm.setInt(7, book.getPages());

            ptstm.executeUpdate();         
            
            ptstm = con.prepareStatement(updateCountSql);
            ptstm.setInt(1, userId);
            
            ptstm.executeUpdate();
            
            con.commit();
            
            return true;
        } catch (SQLException e) {           
            //muito feio meu deus
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(BookDatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            DataBaseConnection.closeConnection(con, ptstm);
        }
        
        return false;
    }

    public static ArrayList<Book> getAllBooksFromUser() {
        ArrayList<Book> books = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement stm = null;
        ResultSet rst = null;
        String SQL = "SELECT * FROM book WHERE FK_idUser = ?";

        int userId = Session.getInstance().getLoggedUser().getIdUser();
        
        try {
            stm = con.prepareStatement(SQL);
            stm.setInt(1, userId);
            
            rst = stm.executeQuery();

            while (rst.next()) {
                String title = rst.getString(3);
                short year = rst.getShort(4);
                String author = rst.getString(5);
                String language = rst.getString(6);
                String isbn = rst.getString(7);
                int pages = rst.getInt(8);

                Book book = new Book(title, year, language, isbn, pages, author);
                System.out.println(book.getAuthor());
                books.add(book);
            }

            return books;
        } catch (SQLException e) {
            System.out.println(e);
        }
        
        return null;
    }
}
