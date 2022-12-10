package br.com.acme.connection;

import br.com.acme.model.Article;
import br.com.acme.model.Session;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArticleDatabaseConnector {

    public static boolean InsertArticle(Article article) {
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement ptstm = null;
        String insertArticleSql = "INSERT INTO article(FK_idUser, title, year, author, doi, journal) "
                + " VALUES(?, ?, ?, ?, ?, ?)";
        
        int userId = Session.getInstance().getLoggedUser().getIdUser();
        
        String updateCountSql = "UPDATE user SET publication_limit = publication_limit - 1 WHERE idUser =  ?";
        
        try {
            con.setAutoCommit(false);
            
            ptstm = con.prepareStatement(insertArticleSql);

            ptstm.setInt(1, article.getFK_idUser());
            ptstm.setString(2, article.getTitle());
            ptstm.setInt(3, article.getYear());
            ptstm.setString(4, article.getAuthor());
            ptstm.setString(5, article.getDoi());
            ptstm.setString(6, article.getJournal());

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
                Logger.getLogger(ArticleDatabaseConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } finally {
            DataBaseConnection.closeConnection(con, ptstm);
        }
        
        return false;
    }

    public static ArrayList<Article> getAllArticlesFromUser(){
        ArrayList<Article> articles = new ArrayList<>();
        Connection con = DataBaseConnection.getConnection();
        PreparedStatement stm = null;
        ResultSet rst = null;
        String SQL = "SELECT * FROM article WHERE FK_idUser = ?";
        
        int userId = Session.getInstance().getLoggedUser().getIdUser();
        
        try {
            stm = con.prepareStatement(SQL);
            stm.setInt(1, userId);
            
            rst = stm.executeQuery();
            
            while(rst.next()){
                String title = rst.getString(3);
                short year = rst.getShort(4);
                String author = rst.getString(5);
                String doi = rst.getString(6);
                String journal = rst.getString(7);
                
                Article article = new Article(title, year, author, doi, journal);
                articles.add(article);
            }
            
            return articles;
        } catch (SQLException e) {
            System.out.println(e);
        }
        
       return null;
    }
    
}
