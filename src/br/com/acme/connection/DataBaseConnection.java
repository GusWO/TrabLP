package br.com.acme.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseConnection {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/library_system";
    private static final String USERNAME = "root";

     private static final ArrayList<String> passwords = new ArrayList() {{
        add("");
        add("root");
        add("admin");
        add("sa");
        add("1234");
    }};
    
    public static Connection getConnection() {
        return getConnection(0);
    }
    
    private static Connection getConnection(int tryIndex) {
        if (tryIndex > passwords.size() - 1) return null;

        try {
            String passwordToTry = passwords.get(tryIndex);
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, passwordToTry);
            return connection;

        } catch (SQLException ex) {
            System.out.println("Trying connection index " + tryIndex + " failed, trying next");
            
            return getConnection(tryIndex + 1);
        }
    }

    public static void closeConnection(Connection conection) {
        try {
            if (conection != null) {
                conection.close();
            }
        } catch (SQLException e) {

        }

    }
    
    public static void closeConnection(Connection conection, PreparedStatement stmt) {
        closeConnection(conection);
        
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {

        }

    }
    
    public static void closeConnection(Connection conection, PreparedStatement stmt, ResultSet rst) {
        closeConnection(conection, stmt);
        
        try {
            if (rst != null) {
                stmt.close();
            }
        } catch (SQLException e) {

        }

    }
    

    

}
