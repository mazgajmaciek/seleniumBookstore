package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BooksPage extends Page {
    @FindBy(id = "title")
    private WebElement titleField;

    @FindBy(id = "author_id")
    private WebElement authorSelect;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(id = "bookEditSelect")
    private WebElement bookEditSelect;

    @FindBy(xpath = "//form/button[contains(.,'Add')]")
    private WebElement addBookBtn;

    @FindBy(xpath = "//ul[@id='booksList']/li/div/span")
    private List<WebElement> booksListTitle;

    @FindBy(xpath = "//ul[@id='booksList']/li/div[@class='panel-body book-description']")
    private List<WebElement> booksListDescription;



    public BooksPage(WebDriver driver) {
        super(driver);
    }

    public void addBookTitle (String title) {
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

    public void addBook (){
        addBookBtn.click();
    }

    public boolean checkIfBookCreatedByName(String newBookTitle) {
        List<WebElement> list = booksListTitle;
        String lastAddedBookTitle = list.get(list.size() - 1).getText();
        if (!list.isEmpty()) {
            if (lastAddedBookTitle.equals(newBookTitle)) {
                return true;
            }
        }
        return false;
}

    public void checkIfDescriptionCreatedByName() {
        List<WebElement> listDescription = booksListDescription;
        String lastAddedBookDescription = listDescription.get(listDescription.size() - 1).getText();
        System.out.println(lastAddedBookDescription);
//        if (!lastAddedBookDescription.isEmpty()) {
//            if (lastAddedBookTitle.equals(newBookTitle)) {
//                return true;
//            }
//        }
//        return false;
//        if bookCreated authorName return true ....
//        return false;
    }

}
