package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GmailLoginPage;
import pages.GmailMainPage;
import pages.GmailPasswordPage;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class GmailTests extends BaseTest {
    public Logger log = Logger.getLogger(BaseTest.class);


    @Test
    public void SendEmailFromDraftsTest() {

/*
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), DesiredCapabilities.chrome());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
*/
        log.info("Login into user`s account");
        GmailLoginPage gmailloginpage = new GmailLoginPage(driver);
        gmailloginpage.open()
                .pressSigninButton()
                .fillEmailIInput(USER_EMAIL);

        GmailPasswordPage gmailpasswordpage = gmailloginpage.pressNextButton();
        gmailpasswordpage.fillGmailPasswordInput(USER_PASSWORD);
        GmailMainPage gmailmainpage = gmailpasswordpage.pressPasswordNextButton();

        log.info("Create email and save it in drafts");
        //gmailmainpage.doubleClickMoreButton();
        gmailmainpage.pressComposeButton()
                .fillRecipentInput(RECIPIENT_EMAIL)
                .fillSubjectInput(EMAIL_SUBJECT)
                .fillBodyInput(EMAIL_BODY)
                .saveAndCloseButton()
                .clickOnDraftsLink();


        log.info("Verify that email is saved in drafts");
        gmailmainpage.clickOnDraftEmail(EMAIL_SUBJECT);
        Assert.assertTrue(gmailmainpage.isEmailAppearedInDrafts(EMAIL_BODY));

        log.info("Send email");
        gmailmainpage.sendButton()
                .sentButton();

        log.info("Verify that email is appeared in sent folder");
        Assert.assertTrue(gmailmainpage.isEmailAppearedInSentFolder(EMAIL_BODY));
        gmailmainpage.clickOnDraftsLink()
                .noDraftsButton();


        log.info("Log out from user`s account");
        gmailmainpage.enterSearchFieldSpace() //checking actions using keyboard

                .imageButton()
                .signOutButton();

    }
}
