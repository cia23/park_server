//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package DataAccess;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.sql.Statement;

public class Database {
    private Connection conn;

    public Database() {
    }

    public Database(Connection conn) {
        this.conn = conn;
    }

    public Connection openConnection() throws DataAccessException {
        try {
            String CONNECTION_URL = "jdbc:sqlite:db.db";
            this.conn = DriverManager.getConnection("jdbc:sqlite:db.db");
            this.conn.setAutoCommit(false);
        } catch (SQLException var2) {
            var2.printStackTrace();
            throw new DataAccessException("Unable to open connection to database");
        }

        return this.conn;
    }

    public Connection getConnection() throws DataAccessException {
        return this.conn == null ? this.openConnection() : this.conn;
    }

    public void closeConnection(boolean commit) throws DataAccessException {
        try {
            if (commit) {
                this.conn.commit();
            } else {
                this.conn.rollback();
            }

            this.conn.close();
            this.conn = null;
        } catch (SQLException var3) {
            var3.printStackTrace();
            throw new DataAccessException("Unable to close database connection");
        }
    }

    public void clearTables() throws DataAccessException {
        Statement stmt;
        String sql;

        try {
            stmt = this.conn.createStatement();

            try {
                sql = "DELETE FROM parks";
                stmt.executeUpdate(sql);
            } catch (Throwable var8) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var14) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }

        try {
            stmt = this.conn.createStatement();

            try {
                sql = "DELETE FROM users";
                stmt.executeUpdate(sql);
            } catch (Throwable var12) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var6) {
                        var12.addSuppressed(var6);
                    }
                }

                throw var12;
            }

            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException var13) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }

        try {
            stmt = this.conn.createStatement();

            try {
                sql = "DELETE FROM parks";
                stmt.executeUpdate(sql);
            } catch (Throwable var10) {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (Throwable var5) {
                        var10.addSuppressed(var5);
                    }
                }

                throw var10;
            }

            if (stmt != null) {
                stmt.close();
            }

        } catch (SQLException var11) {
            throw new DataAccessException("SQL Error encountered while clearing tables");
        }
    }
}