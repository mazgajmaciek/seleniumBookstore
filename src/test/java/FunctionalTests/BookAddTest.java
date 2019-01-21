package FunctionalTests;

import PageObjects.BooksPage;
import org.junit.Test;

public class BookAddTest extends BaseTest{
    @Test
    public void addBook() {
        driver.get("https://www.maciekmazgaj.com/bookstore/frontend/?action=books");

//        BooksPage books = new BooksPage(driver);
        BooksPage books = basePage.setWebDriver(driver);

        //script for waiting for js load
        BooksPage::addBookTitle("blabla");
        BooksPage::addBook();

        booksPage.addBookTitle("new_title");
        booksPage.addBookAuthor();
        booksPage.addBookDesc("new_title_description");
        booksPage.addBook();
    }

    @Test
    public void editBook() {
        driver.get("https://www.maciekmazgaj.com/bookstore/frontend/?action=books");

        BooksPage booksPage = new BooksPage(driver);

    }

}
