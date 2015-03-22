import sun.security.util.*;

import java.sql.*;
import java.util.*;

/**
 * Created by solveigmarianes on 21.03.15.
 */
public class DatabaseConverter implements AutoCloseable {
    private final String USER = "root";
    private final String PASSWORD = "";
    public List<Book> books;
    public ConnectToDB db;

    public DatabaseConverter(ConnectToDB db) throws SQLException {
        setDb(db);
    }

    public DatabaseConverter() throws SQLException {
        setDb(new ConnectToDB("localhost", "pg4100innlevering2", USER, PASSWORD));
    }

    public List<Book> connectAndPopulateBookList() {
        String tableName = "bokliste";
        books = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement stmt = con.createStatement();
             ResultSet res = stmt.executeQuery("SELECT * FROM " + tableName)) {
            while (res.next()) {
                String[] names = res.getNString("forfatter").split(",");

                books.add(new Book(res.getNString("tittel"),
                        names[1].trim(),
                        names[0].trim(),
                        res.getNString("isbn"),
                        res.getInt("sider"),
                        res.getInt("utgitt")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void setDb(ConnectToDB db) {
        this.db = db;
    }

    @Override
    public void close() throws Exception {
        Debug.println("DatabaseConverter ConnectToDB", "Closed");
        db.close();
    }
}
