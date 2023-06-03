package Tests;
import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.UserDAO;
import Model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Database db;
    private User user;
    private UserDAO uDao;

    @BeforeEach
    public void setUp() throws DataAccessException
    {
        db = new Database();
        user = new User("Gale123A", "12345", "u1");
        Connection conn = db.getConnection();
        db.clearTables();
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        this.uDao.insert(this.user);
        User compareTest = this.uDao.find(this.user.getUserID());
        assertNotNull(compareTest);
        assertEquals(this.user, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        uDao.insert(this.user);
        assertThrows(DataAccessException.class, ()-> this.uDao.insert(this.user));
    }

    @Test
    public void findPass() throws DataAccessException {
        this.uDao.insert(this.user);
        User newUser = new User("Frank00", "2468", "u2");
        this.uDao.insert(newUser);
        User compareTest = this.uDao.find(newUser.getUserID());
        assertNotNull(compareTest);
        assertEquals(newUser, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        this.uDao.insert(this.user);
        User newUser = new User("Frank00", "2468", "u2");
        assertNull(this.uDao.find(newUser.getUserID()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        this.uDao.insert(this.user);
        User newUser = new User("Frank00", "2468", "u2");
        this.uDao.insert(newUser);
        ArrayList<User> actualUsers = new ArrayList<>();
        actualUsers.add(this.user);
        actualUsers.add(newUser);
        ArrayList<User> compareTest = this.uDao.findAll();
        assertNotNull(compareTest);
        assertEquals(actualUsers, compareTest);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        this.uDao.insert(this.user);
        User newUser = new User("Frank00", "2468", "u2");
        ArrayList<User> actualUsers = new ArrayList<>();
        actualUsers.add(this.user);
        actualUsers.add(newUser);
        ArrayList<User> compareTest = this.uDao.findAll();
        assertNotNull(compareTest);
        assertNotEquals(actualUsers, compareTest);
    }

    @Test
    public void deletePass() throws DataAccessException {
        this.uDao.insert(this.user);
        User newUser = new User("Frank00", "2468", "u2");
        this.uDao.insert(newUser);
        this.uDao.delete("u2");
        User compareTest = this.uDao.find(newUser.getUserID());
        assertNull(compareTest);
        compareTest = this.uDao.find(this.user.getUserID());
        assertEquals(this.user, compareTest);
    }

    @Test
    public void testClear() throws DataAccessException {
        this.uDao.insert(this.user);
        this.uDao.clearTables();
        User compareTest = this.uDao.find(this.user.getUserID());
        assertNull(compareTest);
    }
}