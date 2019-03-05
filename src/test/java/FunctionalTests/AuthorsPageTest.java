package FunctionalTests;

import PageObjects.AuthorsPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AuthorsPageTest extends BaseTest {
    private static AuthorsPage authorsPage;

    private String newAuthorName = "new_author_name";
    private String newAuthorSurname = "new_author_surname";
    private String newAuthorDescription = "new_author_description";

    private String editAuthorName = "edit_author_name";
    private String editAuthorSurname = "edit_author_surname";
    private String editAuthorDescription = "edit_author_description";

    @BeforeClass
    public static void localSetUp() {
        authorsPage = new AuthorsPage(driver);
        driver.get("https://www.maciekmazgaj.com/bookstore/frontend/?action=authors");
    }

    @Test
    public void addAndEditAuthor() {
        authorsPage.addAuthor(newAuthorName, newAuthorSurname, newAuthorDescription);
        Assert.assertTrue(authorsPage.checkIfAuthorCreatedBy(newAuthorName, newAuthorSurname));
        Assert.assertTrue(authorsPage.checkIfAuthorDescriptionCreatedBy(newAuthorDescription));

        authorsPage.editAuthor(editAuthorName, editAuthorSurname, editAuthorDescription);
        Assert.assertTrue(authorsPage.checkIfAuthorEditedBy(editAuthorName, editAuthorSurname));
        Assert.assertTrue(authorsPage.checkIfAuthorDescriptionEditedBy(editAuthorDescription));
    }
}
