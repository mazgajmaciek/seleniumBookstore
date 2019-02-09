package FunctionalTests;

import PageObjects.BooksPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookAddTest extends BaseTest {

    private static BooksPage booksPage;
    private String newBookTitle = "new_title";
    private String newBookDescription = "new_title_description";
    private String editBookTitle = "edit_title";
    private String editBookDescription = "edit_description";


    @BeforeClass
    public static void localSetUp() {
        booksPage = new BooksPage(driver);
        driver.get("https://www.maciekmazgaj.com/bookstore/frontend/?action=books");
    }

    @Test
    public void addBook() {
        booksPage.addBookTitle(newBookTitle);
        booksPage.addBookAuthor(1);
        booksPage.addBookDesc(newBookDescription);
        booksPage.addBook();

        Assert.assertTrue(booksPage.checkIfBookCreatedByName(newBookTitle));
        Assert.assertTrue(booksPage.checkIfDescriptionCreatedByName(newBookDescription));
    }

    @Test
    public void editBook() {
        booksPage.editBook(editBookTitle, editBookDescription);
        Assert.assertTrue(booksPage.checkIfBookCreatedByName(editBookTitle));
        Assert.assertTrue(booksPage.checkIfDescriptionCreatedByName(editBookDescription));
    }


}
