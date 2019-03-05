package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

public class AuthorsPage extends Page {

    @FindBy(id = "name")
    private WebElement authorName;

    @FindBy(id = "surname")
    private WebElement authorSurname;

    @FindBy(id = "description")
    private WebElement authorDescription;

    @FindBy(xpath = "//form/button[contains(.,'Add')]")
    private WebElement addAuthorBtn;

    @FindBy(id = "authorEditSelect")
    private WebElement authorEditSelect;

    @FindBy(xpath = "//form[@id='authorEdit']/div/input[@id='name']")
    private WebElement authorEditName;

    @FindBy(xpath = "//form[@id='authorEdit']/div/input[@id='surname']")
    private WebElement authorEditSurname;

    @FindBy(xpath = "//form[@id='authorEdit']/div/input[@id='description']")
    private WebElement authorEditDescription;

    @FindBy(xpath = "//form[@id='authorEdit']/button[contains(.,'Edit')]")
    private WebElement editAuthorBtn;

    public AuthorsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement lastAuthorRemoveBtn() {
        return driver.findElement(By.xpath("//ul[@id='authorsList']/li[last()]/div[1]/button[1]"));
    }

    public int authorListSize() {
        return driver.findElements(By.xpath("//ul[@id='authorsList']/li")).size();
    }

    public int currentAuthorListSize;

    public List<WebElement> bookButtonsList() {
        return driver.findElements(By.xpath("//ul[@id='authorsList']/li/div/button[2]"));
    }

    public WebElement lastAuthor() {
        return driver.findElement(By.xpath("//ul[@id='authorsList']/li[last()]/div/span"));
    }

    public WebElement lastAuthorDescription() {
        return driver.findElement(By.xpath("//ul[@id='authorsList']/li[last()]/div[2]"));
    }

    public WebElement lastAuthorDescButton() {
        return driver.findElement(By.xpath("//ul[@id='authorsList']/li[last()]/div[1]/button[3]"));
    }

    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
            } catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public void addAuthor(String name, String surname, String description) {
        authorName.clear();
        authorSurname.clear();
        authorDescription.clear();

        authorName.sendKeys(name);
        authorSurname.sendKeys(surname);
        authorDescription.sendKeys(description);

        addAuthorBtn.click();
        currentAuthorListSize = authorListSize();
    }

    public boolean checkIfAuthorCreatedBy(String name, String surname) {
        waitForJSandJQueryToLoad();
        return lastAuthor().getText().equals(name + " " + surname);
    }

    public boolean checkIfAuthorDescriptionCreatedBy(String description) {
        Actions action = new Actions(driver);
        action.moveToElement(lastAuthorDescButton()).click().perform();
//        action.moveToElement(lastAuthorDescription()).click().perform();
        waitForJSandJQueryToLoad();
        return lastAuthorDescription().getText().equals(description);
    }

    public void editAuthor(String editName, String editSurname, String editDescription) {
        //select last added author
        Select editAuthorDropdown = new Select(authorEditSelect);
        int lastOptionIndex = editAuthorDropdown.getOptions().size() - 1;
        waitForJSandJQueryToLoad();
        editAuthorDropdown.selectByIndex(lastOptionIndex);

        //edit author name
        WebElement editAuthorName = authorEditName;
        editAuthorName.clear();
        editAuthorName.sendKeys(editName);

        //edit author surname
        WebElement editAuthorSurname = authorEditSurname;
        editAuthorSurname.clear();
        editAuthorSurname.sendKeys(editSurname);

        //edit author description
        WebElement editAuthorDescription = authorEditDescription;
        editAuthorDescription.clear();
        editAuthorDescription.sendKeys(editDescription);

        editAuthorBtn.click();
    }

    public boolean checkIfAuthorEditedBy(String editName, String editSurname) {
        waitForJSandJQueryToLoad();
        return lastAuthor().getText().equals(editName + " " + editSurname);
    }

    public boolean checkIfAuthorDescriptionEditedBy(String editDescription) {
        Actions action = new Actions(driver);
        action.moveToElement(lastAuthorDescButton()).click().perform();
        action.moveToElement(lastAuthorDescButton()).click().perform();
        waitForJSandJQueryToLoad();
        return lastAuthorDescription().getText().equals(editDescription);
    }

    public void removeAuthor() {
        currentAuthorListSize = authorListSize();
        lastAuthorRemoveBtn().click();
        waitForJSandJQueryToLoad();
    }

    public boolean checkIfAuthorRemoved() {
        int bookListSizeAfterRemoval = authorListSize();
//        int bookListSizeAfterRemoval = authorListSize();
        return bookListSizeAfterRemoval < currentAuthorListSize;
    }

    public void clickBookButtons() {
        for (WebElement element : bookButtonsList()) {
            element.click();
        }
    }

}
