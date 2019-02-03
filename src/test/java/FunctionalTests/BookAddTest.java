package FunctionalTests;

import PageObjects.BooksPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookAddTest extends BaseTest{

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
    }

    @Test
    public void ifBookCreated() {
        Assert.assertTrue(booksPage.checkIfBookCreatedByName(newBookTitle));
    }

    @Test
    public void ifBookDescriptionCreated() {
        Assert.assertTrue(booksPage.checkIfDescriptionCreatedByName(newBookDescription));
    }

    @Test
    public void ifBookEdited() {
        booksPage.editBook(editBookTitle,editBookDescription);
//        booksPage.checkIfBookEdited(editBookTitle, editBookDescription);
        Assert.assertTrue(booksPage.checkifBookEdited());
    }


}
