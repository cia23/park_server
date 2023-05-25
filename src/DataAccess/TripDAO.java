package DataAccess;

import Model.Trip;

import java.sql.*;
import java.util.ArrayList;

public class TripDAO {
    private final Connection conn;

    public TripDAO(Connection conn)
    {
        this.conn = conn;
    }


    public void insert(Trip trip) throws DataAccessException {
        //We can structure our string to be similar to a sql command, but if we insert question
        //marks we can change them later with help from the statement
        String sql = "INSERT INTO trips (trip_id, user_id, park_id, date) VALUES(?,?,?,?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            //Using the statements built-in set(type) functions we can pick the question mark we want
            //to fill in and give it a proper value. The first argument corresponds to the first
            //question mark found in our sql String
            stmt.setString(1, trip.getTripID());
            stmt.setString(2, trip.getUserID());
            stmt.setString(3, trip.getParkID());
            stmt.setString(4, trip.getDate());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }

    public Trip find(String trip_id) throws DataAccessException {
        Trip trip;
        ResultSet rs = null;
        String sql = "SELECT * FROM trips WHERE trip_id = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, trip_id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                trip = new Trip(rs.getString("trip_id"),  rs.getString("user_id"), rs.getString("park_id"), rs.getString("date"));
                return trip;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding trip");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public ArrayList<Trip> findAll() throws DataAccessException {
        ArrayList<Trip> trips = new ArrayList<>();
        ResultSet rs = null;
        String sql = "SELECT * FROM trips;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            // stmt.setString(1, eventID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Trip trip = new Trip(rs.getString("trip_id"), rs.getString("user_id"), rs.getString("park_id"), rs.getString("date"));
                trips.add(trip);
            }
            return trips;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DataAccessException("Error encountered while finding trips");
        } finally {
            if(rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void delete(String user_id) throws DataAccessException {
        String sql = "DELETE FROM trips WHERE trip_id = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, user_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while deleting trip_id");
        }
    }

    public void clearTables() throws DataAccessException {
        try (Statement stmt = conn.createStatement()) {
            String sql = "DELETE FROM trips";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
