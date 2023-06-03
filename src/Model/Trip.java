package Model;

import java.util.Objects;

public class Trip {

    private String tripID;
    private String userID;
    private String parkID;
    private String date;

    /**
     * empty constructor for User
     */
    public Trip() {
        tripID = null;
        userID = null;
        parkID = null;
        date = null;
    }

    /**
     * User constructor with all parameters
     * @param tripID
     * @param userID
     * @param parkID
     * @param date
     */
    public Trip(String tripID, String userID, String parkID, String date) {
        this.tripID = tripID;
        this.userID = userID;
        this.parkID = parkID;
        this.date = date;
    }

    public String getTripID() {
        return tripID;
    }

    public void setTripID(String tripID) {
        this.tripID = tripID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getParkID() {
        return parkID;
    }

    public void setParkID(String parkID) {
        this.parkID = parkID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return (Objects.equals(userID, trip.userID) &&
                Objects.equals(parkID, trip.parkID) &&
                Objects.equals(date, trip.date));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, parkID, date);
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripID='" + tripID + '\'' +
                ", userID='" + userID + '\'' +
                ", parkID='" + parkID + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}