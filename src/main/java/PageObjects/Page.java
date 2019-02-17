package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Page {
    protected static WebDriver driver;

    public Page(WebDriver driver) {
        setWebDriver(driver);
    }

    public void setWebDriver(WebDriver driver){
        Page.driver = driver;
        //TODO - initElements is not enough for catching elements on websites with JS
        PageFactory.initElements(driver, this);
    }
}