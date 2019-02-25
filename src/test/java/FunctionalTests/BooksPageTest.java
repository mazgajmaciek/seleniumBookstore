package FunctionalTests;

import PageObjects.BooksPage;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BooksPageTest extends BaseTest {

    private static BooksPage booksPage;
    private String newBookTitle = "new_title1";
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

        super.waitForJSandJQueryToLoad();
        Assert.assertTrue(booksPage.checkIfBookCreatedByName(newBookTitle));
        Assert.assertTrue(booksPage.checkIfDescriptionCreatedByName(newBookDescription));
//        Assert.assertTrue(booksPage.clickDescButton());

//        driver.findElement(By.id("title")).clear();
//        String name = "1223344";
//        driver.findElement(By.id("title")).sendKeys(name);
//        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
//        WebElement element = driver.findElement(By.xpath("//span[@class='bookTitle' and .='" + newBookTitle + "']"));
//        Assert.assertEquals(driver.findElements(By.xpath("//li[@class='list-group-item']")).size(), 13);
//        System.out.println(element.getText());
        //Assert.assertEquals(element.getText(), newBookTitle);
    }

//    @Test
//    public void editBook() {
//        booksPage.editBook(editBookTitle, editBookDescription);
//        Assert.assertTrue(booksPage.checkIfBookCreatedByName(editBookTitle));
//
//        //TODO - element not clickable
//        Assert.assertTrue(booksPage.checkIfDescriptionCreatedByName(editBookDescription));
//    }
//
//    @Test
//    public void removeBook() {
//
//    }


}
