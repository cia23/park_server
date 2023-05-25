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

//We will use this to test that our insert method is working and failing in the right ways
public class UserDAOTest {
    private Database db;
    private User testUser;
    private UserDAO uDao;

    //public Person(String personID, String username, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {

    @BeforeEach
    public void setUp() throws DataAccessException
    {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        //String username, String password, String email, String firstName, String lastName, String gender, String personID
        //and a new event with random data
        testUser = new User("Gale123A", "12345", "u1");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        db.clearTables();
        //Then we pass that connection to the EventDAO so it can access the database
        uDao = new UserDAO(conn);
    }

    @AfterEach
    public void tearDown() throws DataAccessException {
        //Here we close the connection to the database file so it can be opened elsewhere.
        //We will leave commit to false because we have no need to save the changes to the database
        //between test cases
        db.closeConnection(false);
    }

    @Test
    public void insertPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        uDao.insert(testUser);
        //So lets use a find method to get the event that we just put in back out
        User compareTest = uDao.find(testUser.getUsername());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(testUser, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        uDao.insert(testUser);
        //Here, we'll open the connection in preparation for the test case to use it
        //but our sql table is set up so that "eventID" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
        assertThrows(DataAccessException.class, ()-> uDao.insert(testUser));
    }

    @Test
    public void findPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        uDao.insert(testUser);
        //Add a new User to see if it can differentiate between multiple rows in database
        User newUser = new User("Frank00", "2468", "u2");
        uDao.insert(newUser);
        //So lets use a find method to get the event that we just put in back out
        User compareTest = uDao.find(newUser.getUsername());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(newUser, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        uDao.insert(testUser);
        User newUser = new User("Frank00", "2468", "u2");
        //Here, we'll open the connection in preparation for the test case to use it
        //but our sql table is set up so that "eventID" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
        assertNull(uDao.find(newUser.getUsername()));
        // assertThrows(DataAccessException.class, ()-> uDao.find(newUser.getUsername()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        uDao.insert(testUser);
        //Add a new User to see if it can differentiate between multiple rows in database
        User newUser = new User("Frank00", "2468", "u2");
        uDao.insert(newUser);
        ArrayList<User> actualUsers = new ArrayList<>();
        actualUsers.add(testUser);
        actualUsers.add(newUser);
        //So lets use a find method to get the event that we just put in back out
        ArrayList<User> compareTest = uDao.findAll();
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(actualUsers, compareTest);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        uDao.insert(testUser);
        //Add a new User to see if it can differentiate between multiple rows in database
        User newUser = new User("Frank00", "2468", "u2");
        // uDao.insert(newUser);
        ArrayList<User> actualUsers = new ArrayList<>();
        actualUsers.add(testUser);
        actualUsers.add(newUser);
        //So lets use a find method to get the event that we just put in back out
        ArrayList<User> compareTest = uDao.findAll();
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertNotEquals(actualUsers, compareTest);
    }

    @Test
    public void deletePass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        uDao.insert(testUser);
        //Event("Biking_123A", "Gale", "Gale123A",
        //                35.9f, 140.1f, "Japan", "Ushiku",
        //                "Biking_Around", 2016);  40.2338° N, 111.6585° W
        //Add a new User to see if it can differentiate between multiple rows in database
        User newUser = new User("Frank00", "2468", "u2");
        uDao.insert(newUser);
        //So lets use a find method to get the event that we just put in back out
        uDao.delete("Frank00");
        User compareTest = uDao.find(newUser.getUsername());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNull(compareTest);
        compareTest = uDao.find(testUser.getUsername());
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(testUser, compareTest);
    }

    @Test
    public void testClear() throws DataAccessException {
        uDao.insert(testUser);
        uDao.clearTables();
        User compareTest = uDao.find(testUser.getID());
        assertNull(compareTest);
    }
}