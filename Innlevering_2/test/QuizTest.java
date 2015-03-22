import org.junit.*;

import static junit.framework.TestCase.*;

public class QuizTest {
    Quiz quiz;
    private Book book;

    @Before
    public void setUp() throws Exception {
        book = new Book("TITLE", "FIRSTNAME", "LASTNAME", "ISBN", 10, 2000);
        quiz = new Quiz();
        quiz.setCurrentBook(book);
    }

    @After
    public void tearDown() throws Exception {
        quiz.close();
    }

    @Test
    public void testAskQuestion() throws Exception {
        String expected = "Hvem har skrevet TITLE (2000)?";
        String actual = quiz.askQuestion();
        assertEquals("Answer is formatted with correct parameters", expected, actual);
    }

    @Test
    public void testAnswersCorrectly() throws Exception {
        assertTrue("Returns true if answer contains first and last name",
                quiz.answersCorrectly("firstName lastName"));
        assertTrue("Returns true if answer contains last and first name",
                quiz.answersCorrectly("lastName firstName"));
        assertFalse("Returns false if answer only contains one name",
                quiz.answersCorrectly("lastName"));
        assertFalse("Returns false if answer contains wrong name",
                quiz.answersCorrectly("wrongName wrongName"));
    }

    @Test
    public void testRespondToAnswer() throws Exception {
        String exptectedTrue = "Riktig!";
        String exptectedFalse = "Feil - det er FIRSTNAME LASTNAME";
        assertEquals("Response to correct answer", exptectedTrue, quiz.respondToAnswer(true));
        assertEquals("Response to wrong answer", exptectedFalse, quiz.respondToAnswer(false));
    }

    @Test
    public void testGetCurrentBook() throws Exception {
        assertEquals("Returns same book", book, quiz.getCurrentBook());
    }

    @Test
    public void testSetCurrentBook() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testGetBooks() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testSetBooks() throws Exception {
        fail("Not implemented yet");
    }

    @Test
    public void testAddToScore() throws Exception {
        quiz.addToScore();
        assertEquals("Score increases", 1, quiz.getScore());
    }

    @Test
    public void testGetScore() throws Exception {
        assertEquals("Score starts at 0", 0, quiz.getScore());
    }
}