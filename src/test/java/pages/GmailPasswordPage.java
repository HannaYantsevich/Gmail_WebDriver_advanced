package pages;

import org.openqa.selenium.By;
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
        waitForElementVisible(By.xpath("//div[@class='Xb9hP']/input[@type='password']"));
        passwordInput.sendKeys(query);
        return new GmailPasswordPage(driver);

    }

    public GmailMainPage pressPasswordNextButton() {
        waitForElementAndClick(driver, nextButton);
        return new GmailMainPage(driver);
    }
}
