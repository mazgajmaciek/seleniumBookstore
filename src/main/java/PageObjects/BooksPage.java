package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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


//    public BooksPage(WebDriver driver) {
//        super(driver);
//    }

    public void addBookTitle (String title) {
        titleField.clear();
        titleField.sendKeys(title);
    }

    public void addBookAuthor() {
        Select dropdown = new Select(authorSelect);
        dropdown.selectByIndex(1);
    }

    public void addBookDesc(String description) {
        descriptionField.clear();
        descriptionField.sendKeys(description);
    }

    public void addBook (){
        addBookBtn.click();
    }

}
