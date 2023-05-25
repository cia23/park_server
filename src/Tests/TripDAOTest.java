package Tests;

import DataAccess.DataAccessException;
import DataAccess.Database;
import DataAccess.TripDAO;
import Model.Trip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TripDAOTest {
    private Database db;
    private Trip testTrip;
    private TripDAO tDao;

    //public Person(String personID, String username, String firstName, String lastName, String gender, String fatherID, String motherID, String spouseID) {

    @BeforeEach
    public void setUp() throws DataAccessException
    {
        //here we can set up any classes or variables we will need for the rest of our tests
        //lets create a new database
        db = new Database();
        testTrip = new Trip("t1", "u1", "p1", "220101");
        //Here, we'll open the connection in preparation for the test case to use it
        Connection conn = db.getConnection();
        //Let's clear the database as well so any lingering data doesn't affect our tests
        db.clearTables();
        //Then we pass that connection to the EventDAO so it can access the database
        tDao = new TripDAO(conn);
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
        tDao.insert(testTrip);
        //So lets use a find method to get the event that we just put in back out
        Trip compareTest = tDao.find(testTrip.getTripID());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(testTrip, compareTest);
    }

    @Test
    public void insertFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        tDao.insert(testTrip);
        //Here, we'll open the connection in preparation for the test case to use it
        //but our sql table is set up so that "eventID" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
        assertThrows(DataAccessException.class, ()-> tDao.insert(testTrip));
    }

    @Test
    public void findPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        tDao.insert(testTrip);
        //Add a Trip to see if it can differentiate between multiple rows in database
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        tDao.insert(newTrip);
        //So lets use a find method to get the event that we just put in back out
        Trip compareTest = tDao.find(newTrip.getDate());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(newTrip, compareTest);
    }

    @Test
    public void findFail() throws DataAccessException {
        //lets do this test again but this time lets try to make it fail
        //if we call the method the first time it will insert it successfully
        tDao.insert(testTrip);
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        //Here, we'll open the connection in preparation for the test case to use it
        //but our sql table is set up so that "eventID" must be unique. So trying to insert it
        //again will cause the method to throw an exception
        //Note: This call uses a lambda function. What a lambda function is is beyond the scope
        //of this class. All you need to know is that this line of code runs the code that
        //comes after the "()->" and expects it to throw an instance of the class in the first parameter.
        assertNull(tDao.find(newTrip.getTripID()));
        // assertThrows(DataAccessException.class, ()-> uDao.find(newUser.getUsername()));
    }

    @Test
    public void findAllPass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        tDao.insert(testTrip);
        //Add a new User to see if it can differentiate between multiple rows in database
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        tDao.insert(newTrip);
        ArrayList<Trip> actualTrips = new ArrayList<>();
        actualTrips.add(testTrip);
        actualTrips.add(newTrip);
        //So lets use a find method to get the event that we just put in back out
        ArrayList<Trip> compareTest = tDao.findAll();
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(actualTrips, compareTest);
    }

    @Test
    public void findAllFail() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        tDao.insert(testTrip);
        //Add a new User to see if it can differentiate between multiple rows in database
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        // uDao.insert(newUser);
        ArrayList<Trip> actualTrips = new ArrayList<>();
        actualTrips.add(testTrip);
        actualTrips.add(newTrip);
        //So lets use a find method to get the event that we just put in back out
        ArrayList<Trip> compareTest = tDao.findAll();
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNotNull(compareTest);
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertNotEquals(actualTrips, compareTest);
    }

    @Test
    public void deletePass() throws DataAccessException {
        //While insert returns a bool we can't use that to verify that our function actually worked
        //only that it ran without causing an error
        tDao.insert(testTrip);
        //Event("Biking_123A", "Gale", "Gale123A",
        //                35.9f, 140.1f, "Japan", "Ushiku",
        //                "Biking_Around", 2016);  40.2338° N, 111.6585° W
        //Add a new User to see if it can differentiate between multiple rows in database
        Trip newTrip = new Trip("t2", "u2", "p2", "230101");
        tDao.insert(newTrip);
        //So lets use a find method to get the event that we just put in back out
        tDao.delete("t2");
        Trip compareTest = tDao.find(newTrip.getTripID());
        //First lets see if our find found anything at all. If it did then we know that if nothing
        //else something was put into our database, since we cleared it in the beginning
        assertNull(compareTest);
        compareTest = tDao.find(testTrip.getTripID());
        //Now lets make sure that what we put in is exactly the same as what we got out. If this
        //passes then we know that our insert did put something in, and that it didn't change the
        //data in any way
        assertEquals(testTrip, compareTest);
    }

    @Test
    public void testClear() throws DataAccessException {
        tDao.insert(testTrip);
        tDao.clearTables();
        Trip compareTest = tDao.find(testTrip.getTripID());
        assertNull(compareTest);
    }
}