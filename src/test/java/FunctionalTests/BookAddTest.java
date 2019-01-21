package FunctionalTests;

import PageObjects.BooksPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookAddTest extends BaseTest{

    BooksPage booksPage;

    @BeforeClass
    public void localSetUp() {
        booksPage = new BooksPage(driver);
        driver.get("https://www.maciekmazgaj.com/bookstore/frontend/?action=books");
    }

    @Test
    public void addBook() {
        booksPage.addBookTitle("new_title");
        booksPage.addBookAuthor();
        booksPage.addBookDesc("new_title_description");
        booksPage.addBook();
    }

    @Test
    public void editBook() {
        booksPage.addBookAuthor(); //some other test, add author Tolkien
        Assert.assertTrue(booksPage.checkIfAuthorCreatedByName("Tolkien"));
    }

}
