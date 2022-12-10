package br.com.acme.connection;

import br.com.acme.model.Book;
import br.com.acme.model.User;
import com.mysql.cj.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author gusta
 */
public class UserDataBaseConnectorTest {

    private static UserDataBaseConnector userDataCon;

    public UserDataBaseConnectorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Connection conn = DataBaseConnection.getConnection();
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e) {
        }
    }

    @After
    public void tearDown() {
        Connection conn = DataBaseConnection.getConnection();
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
        }
    }

    /**
     * Test of insertUser method, of class UserDataBaseConnector.
     */
    @Test
    public void testInsertUser() {
        User user = new User();
        user.setEmail("testando@teste.com");
        user.setLogin("teste");
        user.setPassword("1234");
        boolean userBoo = UserDataBaseConnector.insertUser(user);
        assertFalse(userBoo);

    }

    /**
     * Test of getAllUsers method, of class UserDataBaseConnector.
     */
    @Test
    public void testGetAllUsers() {
       List<User> users = UserDataBaseConnector.getAllUsers();
        assertFalse(users.isEmpty());
    }

}
