package FunctionalTests;

import PageObjects.AuthorsPage;
import PageObjects.BooksPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorsPageTest extends BaseTest {
    private static AuthorsPage authorsPage;

    private String newAuthorName = "new_author_name";
    private String newAuthorSurname = "new_author_surname";
    private String newAuthorDescription = "new_author_description";

    @BeforeClass
    public static void localSetUp() {
        authorsPage = new AuthorsPage(driver);
        driver.get("https://www.maciekmazgaj.com/bookstore/frontend/?action=authors");
    }

//    @Test
//    public void addAuthor() {
//
//        authorsPage.addAuthor(newAuthorName, newAuthorSurname, newAuthorDescription);
//        Assert.assertTrue(authorsPage.checkIfAuthorCreated(name, surname, description));
//    }
}
