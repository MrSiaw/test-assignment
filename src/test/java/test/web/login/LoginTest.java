package test.web.login;

import com.assignment.pages.LoginPage;
import com.assignment.pages.UpcomingEventPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import test.web.BaseWebTest;

@Epic("welcome") //login+register, for example
@Story("login")
public class LoginTest extends BaseWebTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    private static final String VALID_USER_PHONE = "962323422";
    private static final String INVALID_USER_PHONE = "962323422";
    private static final String VALID_USER_PASS = "valid_pass";

    @Test(testName = "Successful login")
    //@AllureId("") should be some id
    public void successfulLogin() {
        UpcomingEventPage upcomingEventPage = new UpcomingEventPage();
        upcomingEventPage.checkIsLoggedIn(false);
        upcomingEventPage
                .openLoginPage()
                .login(VALID_USER_PHONE, VALID_USER_PASS);
        upcomingEventPage.checkIsLoggedIn(false);
    }

    @Test(testName = "неверный пароль")
    public void invalidPassword() {
        new UpcomingEventPage()
                .openLoginPage()
                .login(VALID_USER_PHONE, "213")
                .checkErrorMessageText("The Phone number or password entered is invalid");
    }

    @Test(testName = "пустые поля")
    public void testEmptyFields() {
        new UpcomingEventPage()
                .openLoginPage()
                .login("", "")
                .checkErrorMessageText("");
    }

    @Test(testName = "")
    public void testInvalidPhoneFormat() {
        new UpcomingEventPage()
                .openLoginPage()
                .login(INVALID_USER_PHONE, VALID_USER_PASS)
                .checkErrorMessageText("The Phone number or password entered is invalid");
    }

    @Test(testName = "")
    public void testEmptyPassword() {
        new UpcomingEventPage()
                .openLoginPage()
                .login(INVALID_USER_PHONE, "")
                .checkErrorMessageText("");
    }

    @Test(testName = "")
    public void testShortPhoneNumber() {
        new UpcomingEventPage()
                .openLoginPage()
                .login("12", VALID_USER_PASS)
                .checkErrorMessageText("");
    }

    @Test(testName = "")
    public void testLongPhoneNumber() {
        new UpcomingEventPage()
                .openLoginPage()
                .login("1231245124214312", VALID_USER_PASS)
                .checkErrorMessageText("");
    }

    @Test(testName = "")
    public void testShortPassword() {
        LoginPage loginPage = new UpcomingEventPage().openLoginPage().login("+1234567890", "123");
        //assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test(testName = "")
    public void testLongPassword() {
        //LoginPage loginPage =
        //assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Test
    public void phoneFieldValidation(String phone, String expected) {
        new UpcomingEventPage()
                .openLoginPage()
                .login("+1234567890", "aVeryLongPasswordExceedingLimits");
    }

    @Test
    public void passwordFieldValidation(String password, String expected) {
        new UpcomingEventPage()
                .openLoginPage()
                .login("+1234567890", "aVeryLongPasswordExceedingLimits");
    }

}
