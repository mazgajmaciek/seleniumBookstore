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

import java.util.List;

public class BooksPage extends Page {

    public BooksPage(WebDriver driver) {
        super(driver);
    }

    //initElements
    @FindBy(xpath = "//form[@id='bookAdd']//input[@id='title']")
    private WebElement titleField;

    @FindBy(id = "author_id")
    private WebElement authorSelect;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(xpath = "//form/button[contains(.,'Add')]")
    private WebElement addBookBtn;

    @FindBy(id = "bookEditSelect")
    private WebElement bookEditSelect;

    @FindBy(xpath = "//form[@id='bookEdit']/div/input[@id='title']")
    private WebElement bookEditTitle;

    @FindBy(id = "author_id_edit")
    private WebElement bookEditAuthor;

    @FindBy(xpath = "//form[@id='bookEdit']//textarea[@id='description']")
    private WebElement bookEditDescription;

    @FindBy(xpath = "//form[@id='bookEdit']/button[contains(.,'Edit')]")
    private WebElement editBookBtn;

    @FindBy(xpath = "//ul[@id='booksList']/li/div/span")
    private List<WebElement> booksListTitle;

    @FindBy(xpath = "//ul[@id='booksList']/li/div[@class='panel-body book-description']")
    private List<WebElement> booksListDescription;

    @FindBy(xpath = "//ul[@id='booksList']/li/div/button[2]")
    private List<WebElement> bookDescriptionButton;

    @FindBy(xpath = "//ul[@id='booksList']/li/div/button[1]")
    private List<WebElement> bookDeleteButton;



    public List<WebElement> addBookForm() {
        return driver.findElements(By.xpath("//form[@id='bookAdd']"));
    }

    public List<WebElement> booksListTitle() {
        return driver.findElements(By.xpath("//ul[@id='booksList']/li/div/span"));
    }

    public WebElement lastBookTitle() {
        return driver.findElement(By.xpath("//ul[@id='booksList']/li[last()]/div/span"));
    }

    public WebElement lastBookDescButton() {
        return driver.findElement(By.xpath("//ul[@id='booksList']/li[last()]/div[1]/button[2]"));
    }

    public WebElement lastBookDescription() {
        return driver.findElement(By.xpath("//ul[@id='booksList']/li[last()]/div[2]"));
    }

    public boolean waitForJSandJQueryToLoad() {

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = driver -> {
            try {
                return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
            }
            catch (Exception e) {
                // no jQuery present
                return true;
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState")
                .toString().equals("complete");

        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }

    public void addBookTitle(String title) {
        titleField.clear();
        titleField.sendKeys(title);
    }

    public void addBookAuthor(int index) {
        Select dropdown = new Select(authorSelect);
        dropdown.selectByIndex(index);
    }

    public void addBookDesc(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void addBook() {
        addBookBtn.click();
    }

    public boolean checkIfBookCreatedByName(String newBookTitle) {
        waitForJSandJQueryToLoad();
        return lastBookTitle().getText().equals(newBookTitle);
    }

    public boolean checkIfDescriptionCreatedByName(String newBookDescription) {
        Actions action = new Actions(driver);
        action.moveToElement(lastBookDescButton()).click().perform();
        waitForJSandJQueryToLoad();
        return lastBookDescription().getText().equals(newBookDescription);
    }

    public void editBook(String newTitle, String newDescription) {
        //select last added book
        Select editBookDropdown = new Select(bookEditSelect);
        int lastOptionIndex = editBookDropdown.getOptions().size() - 1;
        editBookDropdown.selectByIndex(lastOptionIndex);

        //edit book title
        WebElement editBookTitle = bookEditTitle;
        editBookTitle.clear();
        editBookTitle.sendKeys(newTitle);

        //select last available author
        Select authorEditDropdown = new Select(bookEditAuthor);
        int lastAuthorIndex = authorEditDropdown.getOptions().size() - 1;
        authorEditDropdown.selectByIndex(lastAuthorIndex);

        //edit book description
        WebElement editBookDescription = bookEditDescription;
        editBookDescription.clear();
        editBookDescription.sendKeys(newDescription);

        //submit edited book
        WebElement editBook = editBookBtn;
        editBook.submit();
    }

    public void removeBook() {

    }

}