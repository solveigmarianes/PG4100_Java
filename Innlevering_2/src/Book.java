/**
 * Created by solveigmarianes on 21.03.15.
 */
public class Book {
    private String firstName, lastName, title, isbn;
    private int publishedYear, pages;

    public Book(String title, String firstName, String lastName, String isbn, int pages, int publishedYear) {
        setTitle(title);
        setFirstName(firstName);
        setLastName(lastName);
        setIsbn(isbn);
        setPages(pages);
        setPublishedYear(publishedYear);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
