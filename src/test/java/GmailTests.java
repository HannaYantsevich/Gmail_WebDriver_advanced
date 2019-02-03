import pages.GmailLoginPage;
import pages.GmailMainPage;
import pages.GmailPasswordPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class GmailTests {

    private WebDriver driver;

    @BeforeClass(description = ("Start browser"))

    public void startBrowser() {
        // System.setProperty("webdriver.chrome.driver",
        // "/Users/hanna_yantsevich/eclipse-workspace/Gmail/WebDriver/chromedriver");
/*
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
*/

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void GmailTest() throws InterruptedException {

        GmailLoginPage gmailloginpage = new GmailLoginPage(driver);
        gmailloginpage.open().pressSigninButton().fillEmailIInput("HannaTest34@gmail.com");

        GmailPasswordPage gmailpasswordpage = gmailloginpage.pressNextButton();
        gmailpasswordpage.fillGmailPasswordInput("PasswordPassword");

        Thread.sleep(1000);
        GmailMainPage gmailmainpage = gmailpasswordpage.pressNextButton();
        gmailmainpage.doubleClickMoreButton();
        gmailmainpage.pressComposeButton();
        gmailmainpage.recipentInput().subjectInput().bodyInput().saveAndCloseButton();
        //gmailmainpage.createNewEmailAndSaveItInDrafts();
        Thread.sleep(1000);
        gmailmainpage.draftButton().clickOnDraftEmail().sendButton();
        gmailmainpage.sentButton();
        gmailmainpage.draftsButton().noDraftsButton();
        gmailmainpage.enterSearchFieldSpace();
        gmailmainpage.imageButton().signOutButton();

    }

    @AfterClass(description = "Close browser")

    public void kill() {
        driver.quit();
    }

}
