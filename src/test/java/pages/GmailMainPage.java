package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import tests.BaseTest;

import java.sql.Driver;

public class GmailMainPage extends AbstractedPage {

    @FindBy(xpath = "//span[@class='ait']/div[@class='G-asx T-I-J3 J-J5-Ji']")
    private WebElement expandMoreButton;

    @FindBy(css = ".aic .z0 div")
    private WebElement composeButton;

    @FindBy(xpath = "//textarea[@name='to']")
    private WebElement recipientInput;

    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@class='Am Al editable LW-avf']")
    private WebElement bodyInput;

    @FindBy(xpath = "//td[@class='Hm']/img[@class='Ha']")
    private WebElement saveAndClose;

    @FindBy(xpath = "//a[contains(text(), 'Drafts')]")
    private WebElement draftButton;


    @FindBy(xpath = "//div[text()='Send']")
    private WebElement sendButton;

    @FindBy(xpath = "//*[@title='Sent']")
    private WebElement sentButton;

    @FindBy(xpath = "//tr[@class='TD']/td[@class='TC']")
    private WebElement noDrafts;

    @FindBy(xpath = "//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/mail&service=mail']")
    private WebElement imageButton;

    @FindBy(xpath = "//*[contains(text(), 'Sign out')]")
    private WebElement signOutButton;

    @FindBy(xpath = "//input[@placeholder = 'Search mail']")
    private WebElement searchField;


    public GmailMainPage(WebDriver driver) {
        super(driver);
    }


    public GmailMainPage enterSearchFieldSpace() {
        waitForElementPresent(searchField);
        highlightElement(driver, searchField);
        new Actions(driver).sendKeys(Keys.SPACE).build().perform();
        unhighlightElement(driver, searchField);
        return new GmailMainPage(driver);
    }

    public void doubleClickMoreButton() {
        waitForElementPresent(expandMoreButton);
        highlightElement(driver, expandMoreButton);
        new Actions(driver).doubleClick().build().perform();
        unhighlightElement(driver, expandMoreButton);
    }


    public GmailMainPage pressComposeButton() {
        waitForElementPresent(composeButton);
        highlightElement(driver, composeButton);
        composeButton.click();
        unhighlightElement(driver, composeButton);
        return new GmailMainPage(driver);
    }

    public GmailMainPage fillRecipentInput(String recipientQuery) {
        waitForElementVisible(By.xpath("//textarea[@name='to']"));
        highlightElement(driver, recipientInput);
        recipientInput.sendKeys(recipientQuery);
        unhighlightElement(driver, recipientInput);
        return new GmailMainPage(driver);
    }

    public GmailMainPage fillSubjectInput(String subjectQuery) {
        waitForElementVisible(By.xpath("//input[@name='subjectbox']"));
        highlightElement(driver, subjectInput);
        subjectInput.sendKeys(subjectQuery);
        unhighlightElement(driver, subjectInput);
        return this;
    }

    public GmailMainPage fillBodyInput(String bodyQuery) {
        waitForElementVisible(By.xpath("//div[@class='Am Al editable LW-avf']"));
        bodyInput.sendKeys(bodyQuery);
        return this;
    }

    public GmailMainPage saveAndCloseButton() {
        waitForElementVisible(By.xpath("//td[@class='Hm']/img[@class='Ha']"));
        saveAndClose.click();
        return this;
    }

    public GmailMainPage clickOnDraftsLink() {
        waitForElementAndClick(driver, By.xpath("//a[contains(text(), 'Drafts')]"));
        draftButton.click();
        return this;
    }

    public boolean isEmailAppearedInDrafts(String emailBody) {
        waitForElementVisible(By.xpath(String.format("/descendant::span[contains(text(), '%s')][1]", emailBody)));
        return driver.findElement(By.xpath(String.format("/descendant::span[contains(text(), '%s')][1]", emailBody))).isDisplayed();
    }


    public GmailMainPage clickOnDraftEmail(String emailSubject) {
        waitForElementAndClick(driver, By.xpath(String.format("./descendant::span[contains(text(), '%s')][2]", emailSubject)));
        return this;

    }


    public GmailMainPage sendButton() {
        sendButton.click();
        return this;
    }

    public GmailMainPage sentButton() {
        sentButton.click();
        return this;
    }

    public boolean isEmailAppearedInSentFolder(String emailBody) {
        waitForElementVisible(By.xpath(String.format("/descendant::span[contains(text(), '%s')][5]", emailBody)));
        return driver.findElement(By.xpath(String.format("/descendant::span[contains(text(), '%s')][5]", emailBody))).isDisplayed();
    }

    public GmailMainPage noDraftsButton() {
        noDrafts.click();
        return this;

    }

    public GmailMainPage imageButton() {
        imageButton.click();
        return this;
    }

    public void signOutButton() {
        signOutButton.click();
    }


}
