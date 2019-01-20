package FunctionalTests;

import PageObjects.BooksPage;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class FunctionalTest {
    protected static WebDriver driver;

    @Before
    static public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        new BooksPage(driver);
    }

    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}