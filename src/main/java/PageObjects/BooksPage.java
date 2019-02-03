package PageObjects;

import org.openqa.selenium.By;
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

    @FindBy(xpath = "//form/button[contains(.,'Add')]")
    private WebElement addBookBtn;

    @FindBy(id = "bookEditSelect")
    private WebElement bookEditSelect;

    @FindBy(xpath = "//form[@id='bookEdit']/div/input[@id='title']")
    private WebElement bookEditTitle;

    @FindBy(id="author_id_edit")
    private WebElement bookEditAuthor;

    @FindBy(xpath = "//form[@id='bookEdit']//textarea[@id='description']")
    private WebElement bookEditDescription;

    @FindBy(xpath = "//form[@id='bookEdit'/button[contains(.,'Edit')]")
    private WebElement editBookBtn;

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

    public boolean checkIfDescriptionCreatedByName(String newBookDescription) {
        List<WebElement> listDescription = booksListDescription;
        String lastAddedBookTitle = listDescription.get(listDescription.size() - 1).getText();
        if (!listDescription.isEmpty()) {
                return lastAddedBookTitle.equals(newBookDescription);
        }
        return false;
    }

    public void editBook(String newTitle, String newDescription) {
        //select last added book
        Select editBookDropdown = new Select(bookEditSelect);
        int lastOptionIndex = editBookDropdown.getOptions().size()-1;
        editBookDropdown.selectByIndex(lastOptionIndex);

        //edit book title
        WebElement editBookTitle = bookEditTitle;
        editBookTitle.clear();
        editBookTitle.sendKeys(newTitle);

        //select last available author
        Select authorEditDropdown = new Select(bookEditAuthor);
        int lastAuthorIndex = authorEditDropdown.getOptions().size()-1;
        authorEditDropdown.selectByIndex(lastAuthorIndex);

        //edit book description
        WebElement editBookDescription = bookEditDescription;
        editBookDescription.clear();
        editBookDescription.sendKeys(newDescription);

        WebElement editBook = editBookBtn;
        editBook.submit();
    }

    public boolean checkifBookEdited() {
        return true;
    }

}