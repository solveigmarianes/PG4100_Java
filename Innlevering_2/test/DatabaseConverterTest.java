import org.easymock.*;
import org.junit.*;
import org.junit.runner.*;

import java.sql.*;
import java.util.*;

import static junit.framework.TestCase.*;
import static org.easymock.EasyMock.*;

@RunWith(EasyMockRunner.class)
public class DatabaseConverterTest extends EasyMockSupport {
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
        db = createNiceMock(ConnectToDB.class);
        con = createNiceMock(Connection.class);
        stmt = createNiceMock(Statement.class);
        res = createNiceMock(ResultSet.class);
        classUnderTest = new DatabaseConverter(db);
    }

    @Test
    public void testConnectAndPopulateBookList() throws Exception {
        String query = "SELECT * FROM bokliste";
        expect(db.getConnection()).andReturn(con);
        expect(con.createStatement()).andReturn(stmt);
        expect(stmt.executeQuery(query)).andReturn(res);
        expect(res.next()).andReturn(false);
        closeAllResources();
        
        replayAll();
        List<Book> bookList = classUnderTest.connectAndPopulateBookList();
        assertNotNull(bookList);

        verifyAll();

    }

    @Test
    public void testClose() throws Exception {
        classUnderTest.close();
        expectLastCall().once();
    }

    private void closeAllResources() throws Exception {
        res.close();
        stmt.close();
        con.close();
    }
}