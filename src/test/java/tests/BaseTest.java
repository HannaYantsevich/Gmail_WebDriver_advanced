package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    public static final String BASE_URL = "https://www.google.com/intl/ru/gmail/about/#";
    public static final String USER_EMAIL = "HannaTest34@gmail.com";
    public static final String USER_PASSWORD = "PasswordPassword";
    public static final String RECIPIENT_EMAIL = "h.yantsevich@gmail.com";
    public static final String EMAIL_SUBJECT = "Test";
    public static final String EMAIL_BODY = "Hello, World!";


    protected WebDriver driver;
    private Logger log = Logger.getLogger(BaseTest.class);

    @BeforeClass
    public void startBrowserAndOpenBaseURL() {
        WebDriverManager.chromedriver().setup();
        log.info("Start browser");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        //driver.manage().window().maximize();

        log.info("Navigate to home page");
        driver.get(BASE_URL);
    }

    @AfterClass
    public void closeBrowser() {
        log.info("Close browser");
        driver.quit();
    }
}
