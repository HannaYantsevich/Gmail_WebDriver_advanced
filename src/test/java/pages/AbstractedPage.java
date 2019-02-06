package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;


public class AbstractedPage {


    protected WebDriver driver;
    private static final int WAIT_FOR_ELEMENT_SECONDS = 10;


    public AbstractedPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public boolean isElementExists(By by) {
        return !driver.findElements(by).isEmpty();
    }

    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }


    protected void waitForElementPresent(WebElement elem) {
        new WebDriverWait(driver, WAIT_FOR_ELEMENT_SECONDS)
                .until(ExpectedConditions.visibilityOf(elem));

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