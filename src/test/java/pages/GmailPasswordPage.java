package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailPasswordPage extends AbstractedPage {
    public GmailPasswordPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='Xb9hP']/input[@type='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//*[@id='passwordNext']")
    private WebElement nextButton;

    public GmailPasswordPage fillGmailPasswordInput(String query) {
        passwordInput.sendKeys(query);
        return new GmailPasswordPage(driver);

    }

    public GmailMainPage pressNextButton() {
        nextButton.click();
        return new GmailMainPage(driver);
    }
}
