package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
        //TODO - List<WebElement> list won't work here, because when it gets created within the method, the references to webpage elements might have already changed
//        List<WebElement> list = booksListTitle();
//        String lastAddedBookTitle = list.get(list.size() - 1).getText();
        //TODO lastBookTitle() always return last bookList element via xpath
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        wait.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //TODO - looks like I need to wait for book to be added to the list before checking the new title - waiting for AJAX to finish?
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return lastBookTitle().getText().equals(newBookTitle);
//            WebElement el = lastBookTitle();
//            String elS = el.getText();
//            boolean bools = elS.equals(newBookTitle);
//            return bools;
    }

    public boolean clickDescButton() {
        lastBookDescButton().click();
        //TODO - calling WebDRiverWait within PageObject method?
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.attributeToBe(lastBookDescription(), "style", "display: block;"));
    }

    public boolean checkIfDescriptionCreatedByName(String newBookDescription) {
        clickDescButton();
        return lastBookDescription().getText().equals(newBookDescription);
//        WebElement descriptionBtn = bookDescriptionButton;
//        WebElement lastListButton = bookListButtons.get(bookListButtons.size() - 1);
//        lastListButton.click();
//
//        return true;
//
//        WebDriverWait wait = new WebDriverWait(driver, 5);
//        descriptionBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[style=\"display: block;\"]")));
//
//        String lastAddedBookDescription = listDescription.get(listDescription.size() - 1).getText();
//        if (!listDescription.isEmpty()) {
//                return lastAddedBookDescription.equals(newBookDescription);
//        }
//        return false;
    }

//    public void editBook(String newTitle, String newDescription) {
//        //select last added book
//        Select editBookDropdown = new Select(bookEditSelect);
//        int lastOptionIndex = editBookDropdown.getOptions().size() - 1;
//        editBookDropdown.selectByIndex(lastOptionIndex);
//
//        //edit book title
//        WebElement editBookTitle = bookEditTitle;
//        editBookTitle.clear();
//        editBookTitle.sendKeys(newTitle);
//
//        //select last available author
//        Select authorEditDropdown = new Select(bookEditAuthor);
//        int lastAuthorIndex = authorEditDropdown.getOptions().size() - 1;
//        authorEditDropdown.selectByIndex(lastAuthorIndex);
//
//        //edit book description
//        WebElement editBookDescription = bookEditDescription;
//        editBookDescription.clear();
//        editBookDescription.sendKeys(newDescription);
//
//        //submit edited book
//        WebElement editBook = editBookBtn;
//        editBook.submit();
//    }

    public void removeBook() {

    }

}