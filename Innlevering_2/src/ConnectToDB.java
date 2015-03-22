import com.mysql.jdbc.jdbc2.optional.*;
import sun.security.util.*;

import java.sql.*;

public class ConnectToDB implements AutoCloseable {
    public Connection con;
    private String bruker, passord, host, db;
    private MysqlDataSource ds = new MysqlDataSource();

    public ConnectToDB(String bruker, String passord) throws SQLException {
        this("localhost", "test", bruker, passord);
    }

    public ConnectToDB(String host, String db, String bruker, String passord) throws SQLException {
        setDb(db);
        setHost(host);
        setBruker(bruker);
        setPassord(passord);
        ds.setDatabaseName(getDb());
        ds.setServerName(getHost());
        ds.setUser(getBruker());
        ds.setPassword(getPassord());
        con = ds.getConnection();
    }

    @Override
    public void close() throws SQLException {
        Debug.println("ConnectToDB Connection", "Closed");
        con.close();
    }

    public Connection getConnection() throws SQLException {
        return con;
    }

    public String getBruker() {
        return bruker;
    }

    public void setBruker(String bruker) {
        this.bruker = bruker;
    }

    private String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }
}