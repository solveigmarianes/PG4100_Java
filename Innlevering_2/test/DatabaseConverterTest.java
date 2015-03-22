import com.mysql.jdbc.jdbc2.optional.*;
import org.easymock.*;
import org.junit.*;

import java.sql.*;
import java.util.*;

import static junit.framework.TestCase.*;
import static org.easymock.EasyMock.*;

public class DatabaseConverterTest extends EasyMockSupport {
    Book book;
    @Mock
    MysqlDataSource ds;
    @Mock
    ConnectToDB db;
    @Mock
    Connection con;
    @Mock
    Statement stmt;
    @Mock
    ResultSet res;
    @TestSubject
    private DatabaseConverter classUnderTest;

    @Before
    public void setUp() throws Exception {
        db = new ConnectToDB("root", "");
        con = createNiceMock(Connection.class);
        stmt = createNiceMock(Statement.class);
        res = createNiceMock(ResultSet.class);
        classUnderTest = new DatabaseConverter(db);
        book = new Book("title", "name", "name", "isbn", 0, 0);
        //ds = createNiceMock(MysqlDataSource.class);
    }

    @Test
    public void testConnectAndPopulateBookList() throws Exception {
        String query = "SELECT * FROM bokliste";
        classUnderTest.books = new ArrayList<>();
        expect(con.createStatement()).andReturn(stmt);
        expect(stmt.executeQuery(query)).andReturn(res);
        expect(res.next()).andReturn(true);
        classUnderTest.books.add(book);

        replayAll();

        verifyAll();

    }

    @Test
    public void testClose() throws Exception {
        expect(db.getConnection()).andReturn(con);
        replayAll();
        assertFalse(con.isClosed());
        con.close();
        replayAll();
        assertTrue(con.isClosed());
    }
}