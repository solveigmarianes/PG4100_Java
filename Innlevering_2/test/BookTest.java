import org.junit.*;

import static junit.framework.TestCase.*;

public class BookTest {
    private Book book;

    @Before
    public void setUp() {
        book = new Book("title", "firstName", "lastName", "isbn", 100, 2000);
    }

    @Test
    public void testGetFirstName() throws Exception {
        assertEquals("firstName", book.getFirstName());
    }

    @Test
    public void testSetFirstName() throws Exception {
        book.setFirstName("someOtherName");
        assertEquals("someOtherName", book.getFirstName());

    }

    @Test
    public void testGetLastName() throws Exception {
        assertEquals("lastName", book.getLastName());
    }

    @Test
    public void testSetLastName() throws Exception {
        book.setLastName("someOtherName");
        assertEquals("someOtherName", book.getLastName());
    }

    @Test
    public void testGetTitle() throws Exception {
        assertEquals("title", book.getTitle());
    }

    @Test
    public void testSetTitle() throws Exception {
        book.setTitle("someOtherTitle");
        assertEquals("someOtherTitle", book.getTitle());
    }

    @Test
    public void testGetIsbn() throws Exception {
        assertEquals("isbn", book.getIsbn());
    }

    @Test
    public void testSetIsbn() throws Exception {
        book.setIsbn("someOtherIsbn");
        assertEquals("someOtherIsbn", book.getIsbn());
    }

    @Test
    public void testGetPublishedYear() throws Exception {
        assertEquals(2000, book.getPublishedYear());
    }
}