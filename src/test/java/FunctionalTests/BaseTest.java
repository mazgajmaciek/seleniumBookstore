package FunctionalTests;

import PageObjects.Page;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    private static WebDriver driver;
    protected static Page basePage;

    @Before
    static public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        basePage = new Page();
        basePage.setWebDriver(driver);
    }

    @After
    public void tearDown() {
        driver.manage().deleteAllCookies();
        driver.close();
    }
}