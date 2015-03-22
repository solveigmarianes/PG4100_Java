import sun.security.util.*;

import java.sql.*;
import java.util.*;

/**
 * Created by solveigmarianes on 18.03.15.
 */
public class Quiz implements AutoCloseable {
    private int score;
    private List<Book> books;
    private Book currentBook;
    private DatabaseConverter dbConverter;

    public Quiz() throws SQLException {
        dbConverter = new DatabaseConverter();
        setBooks(dbConverter);
        this.score = 0;
    }

    public String askQuestion() {
        StringBuilder string = new StringBuilder();
        return string.append("Hvem har skrevet ")
                .append(getCurrentBook().getTitle())
                .append(" (")
                .append(getCurrentBook().getPublishedYear())
                .append(")?").toString();
    }

    public boolean answersCorrectly(String answer) {
        answer = answer.toUpperCase();       // ignores case of answer-String
        if (answer.contains(getCurrentBook().getFirstName()) && answer.contains(getCurrentBook().getLastName()))
            return true;
        else return false;
    }

    public String respondToAnswer(boolean answersCorrectly) {
        if (answersCorrectly) {
            addToScore();
            return "Riktig!";
        } else {
            return "Feil - det er " + getCurrentBook().getFirstName() + " " + getCurrentBook().getLastName();
        }
    }

    public Book getCurrentBook() {
        return currentBook;
    }

    public void setCurrentBook(Book currentBook) {
        this.currentBook = currentBook;
    }

    public void getNewBookFromList() {
        int count = getBooks().size();
        setCurrentBook(getBooks().get((int) (Math.random() * count)));
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(DatabaseConverter dbConverter) throws SQLException {
        this.books = dbConverter.connectAndPopulateBookList();
    }

    public int addToScore() {
        return (score++);
    }

    public int getScore() {
        return score;
    }

    @Override
    public void close() throws Exception {
        Debug.println("Quiz DatabaseConverter", "Closed");
        dbConverter.close();
    }
}
