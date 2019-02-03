package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import static utils.RandomString.getRandomString;

public class AbstractedPage {
    private Logger log = Logger.getLogger(AbstractedPage.class);

    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 20;

    public static final String RECEIVER = "h.yantsevich@gmail.com";
    public static final String SUBJECT = getRandomString(15);
    public static final String BODY = getRandomString(25);

    public AbstractedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public boolean isElementExists(WebDriver driver, By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            log.info("Element with locator " + by + " does not exist");
            return false;
        }
    }

    protected void waitForElementVisible(WebElement element) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOf(element));

    }

    protected void waitForElementPresent(WebElement elem) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOf(elem));

    }

    public void moveToElement(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
    }

    public void waitForElementAndClick(WebDriver driver, By by) {
        new WebDriverWait(driver, 15).ignoring(StaleElementReferenceException.class, WebDriverException.class)
                .until(ExpectedConditions.elementToBeClickable(by));
        driver.findElement(by).click();
    }

    public void clickOnElementByJS(WebElement element) {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", element);
    }

    protected void highlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);

    }

    protected void unhighlightElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);

    }
}