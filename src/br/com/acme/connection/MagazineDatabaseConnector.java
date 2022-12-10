package br.com.acme.connection;

import br.com.acme.model.Magazine;
import br.com.acme.model.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MagazineDatabaseConnector {

    public static boolean insertMagazine(Magazine magazine) {
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ptstm = null;
        String insertArticleSql = "INSERT INTO magazine(FK_idUser, title, year, author, issue) "
                + " VALUES(?, ?, ?, ?, ?)";
        
        int userId = Session.getInstance().getLoggedUser().getIdUser();
        
        String updateCountSql = "UPDATE user SET publication_limit = publication_limit - 1 WHERE idUser =  ?";
        
        try {
            con.setAutoCommit(false);
            
            ptstm = con.prepareStatement(insertArticleSql);

            ptstm.setInt(1, magazine.getUserId());
            ptstm.setString(2, magazine.getTitle());
            ptstm.setInt(3, magazine.getYear());
            ptstm.setString(4, magazine.getAuthor());
            ptstm.setString(5, magazine.getIssue());

            ptstm.executeUpdate();
            
            ptstm = con.prepareStatement(updateCountSql);
            ptstm.setInt(1, userId);
            
            ptstm.executeUpdate();
            
            con.commit();
            
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            
            //gore abaixo
            try {
                con.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(MagazineDatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } finally {
            DataBaseConnection.closeConnection(con, ptstm);
        }
        
        return false;
    }

    public static ArrayList<Magazine> getAllMagazinesFromUser(){
        ArrayList<Magazine> magazines = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement stm = null;
        ResultSet rst = null;
        String SQL = "SELECT * FROM magazine WHERE FK_idUser = ?";
        
        int userId = Session.getInstance().getLoggedUser().getIdUser();
        
        try {
            stm = con.prepareStatement(SQL);
            stm.setInt(1, userId);
            
            rst = stm.executeQuery();
            
            while(rst.next()){
                String title = rst.getString(3);
                short year = rst.getShort(4);
                String author = rst.getString(5);
                String issue = rst.getString(6);
                
                Magazine magazine = new Magazine(title, year, author, issue);
                magazines.add(magazine);
            }
            
            return magazines;
        } catch (SQLException e) {
            System.out.println(e);
        }
        
       return null;
    }
    
}
