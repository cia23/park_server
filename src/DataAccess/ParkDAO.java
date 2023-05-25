package DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Model.Park;


public class ParkDAO {
    private final Connection conn;

    public ParkDAO(Connection conn) {
        this.conn = conn;
    }

    public void insert(Park park) throws DataAccessException {
        String sql = "INSERT INTO parks (id, name, latitude, longitude, state) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            try {
                stmt.setString(1, park.getParkID());
                stmt.setString(2, park.getName());
                stmt.setFloat(3, park.getLatitude());
                stmt.setFloat(4, park.getLongitude());
                stmt.setString(5, park.getState());
                stmt.executeUpdate();
            } catch (Throwable var7) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException var8) {
            throw new DataAccessException("Error encountered while inserting into the database");
        }
    }


    public Park find(String parkID) throws DataAccessException {
        ResultSet rs = null;
        String sql = "SELECT * FROM parks WHERE id = ?;";

        Park var6;
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            label147: {
                try {
                    stmt.setString(1, parkID);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        Park park = new Park(rs.getString("id"), rs.getString("name"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("state"));
                        var6 = park;
                        break label147;
                    }
                } catch (Throwable var19) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var18) {
                            var19.addSuppressed(var18);
                        }
                    }

                    throw var19;
                }

                if (stmt != null) {
                    stmt.close();
                }

                return null;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var20) {
            var20.printStackTrace();
            throw new DataAccessException("Error encountered while finding park");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var17) {
                    var17.printStackTrace();
                }
            }

        }

        return var6;
    }



    public ArrayList<Park> findAll() throws DataAccessException {
        ArrayList<Park> parks = new ArrayList();
        ResultSet rs = null;
        String sql = "SELECT * FROM parks;";

        ArrayList var20;
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            try {
                rs = stmt.executeQuery();

                while(rs.next()) {
                    Park park = new Park(rs.getString("id"), rs.getString("name"), rs.getFloat("latitude"), rs.getFloat("longitude"), rs.getString("state"));
                    parks.add(park);
                }

                var20 = parks;
            } catch (Throwable var17) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var16) {
                        var17.addSuppressed(var16);
                    }
                }

                throw var17;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var18) {
            var18.printStackTrace();
            throw new DataAccessException("Error encountered while finding parks");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException var15) {
                    var15.printStackTrace();
                }
            }

        }

        return var20;
    }

    public void delete(String name) throws DataAccessException {
        String sql = "DELETE FROM Parks WHERE name = ?;";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            try {
                stmt.setString(1, name);
                stmt.executeUpdate();
            } catch (Throwable var7) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException var8) {
            throw new DataAccessException("SQL Error encountered while deleteing name");
        }
    }

    public void deletePark(String parkID) throws DataAccessException {
        String sql = "DELETE FROM parks WHERE id = ?;";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            try {
                stmt.setString(1, parkID);
                stmt.executeUpdate();
            } catch (Throwable var7) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException var8) {
            throw new DataAccessException("SQL Error encountered while deleteing park");
        }
    }

    public void clearTables() throws DataAccessException {
        try {
            Statement stmt = this.conn.createStatement();

            try {
                String sql = "DELETE FROM Parks";
                stmt.executeUpdate(sql);
            } catch (Throwable var5) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var4) {
                        var5.addSuppressed(var4);
                    }
                }

                throw var5;
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException var6) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}
