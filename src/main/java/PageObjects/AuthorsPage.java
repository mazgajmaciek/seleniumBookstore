package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public AuthorsPage(WebDriver driver) {
        super(driver);
    }

    public void addAuthor (String name, String surname, String description) {
        authorName.clear();
        authorSurname.clear();
        authorDescription.clear();

        authorName.sendKeys(name);
        authorSurname.sendKeys(surname);
        authorDescription.sendKeys(description);

        addAuthorBtn.click();
    }

//    public boolean checkIfAuthorCreated(String name, String surname, String description) {
//
//    }

//    public boolean checkIfAuthorCreatedByName(String name, String surname, String description) {
//        List<WebElement> list = booksListTitle;
//        String lastAddedBookTitle = list.get(list.size() - 1).getText();
//        if (!list.isEmpty()) {
//            if (lastAddedBookTitle.equals(newBookTitle)) {
//                return true;
//            }
//        }
//        return false;
//    }

}
