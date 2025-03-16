package test.web.login;

import com.assignment.pages.LoginPage;
import com.assignment.pages.UpcomingEventPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test.web.BaseWebTest;

@Epic("welcome") //login+register, for example
@Story("login")
public class LoginTest extends BaseWebTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    private static final String VALID_USER_PHONE = "962323422";
    private static final String INVALID_USER_PHONE = "962323422";
    private static final String VALID_USER_PASS = "valid_pass";

    //region some tech
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

    //endregion

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


    //Phone field validation
    //Boundary Value
    //I believe that in this case min == max, so let it be just X (numbers count)
    //data provider:
    // X-1; true
    // X; false
    // X+1; false
    @Test
    public void phoneFieldValidation() {
        // input given phone number (arg1)
        // error message should (not) be displayed (arg2)
    }

    //Phone field prefix validation
    //Boundary
    //correct prefix should be in a range, for example 95 <= Y <= 97
    //data provider:
    // 945555555; true
    // 955555555; false
    // 965555555; false
    // 975555555; false
    // 985555555; true
    @Test
    public void phoneFieldPrefixValidation() {
        // input given phone number (arg1)
        // error message with text 'Mobile provider prefix is not correct.' should(not) be displayed (arg2)
    }

    //Phone field format
    //Equivalence
    //data provider:
    // 'abFD2'; '2'
    // '!@.,#$^&*()[]+2'; '2'  //BTW, In fact, this is not how the system behaves. I believe it should be '2' after format, a bug?
    // ' 2 3 4'; '234' //again, not actual behavior
    @Test
    public void phoneFieldFormat() {
        // input given string into phone number (arg1)
        // get phone number field text and assert with expected (arg2)
    }

    //Password field password
    //Boundary Value
    //6 is min, 16 is max (based on prod)
    //data provider:
    // pas12; 'The password should have length: 6 and the actual provide is 5'
    // pas123; false
    // pas1234; false
    // string_with_length_15; false
    // string_with_length_16; false
    // string_with_length_17; "The password must be maximum 16 characters."
    @Test
    public void passwordFieldValidation() {
        // input given password (arg1)
        // error message should (not) be displayed with text (arg2)
    }

    //Password format
    //Equivalence
    //data provider:
    // 'abFD2'; 'abFD2'
    // '!@.,#$^&*()[]+2'; '!@.,#$^&*()[]+2'
    // ' 2 3 4'; '234' //again, not actual behavior, a space in a password is okay?
    @Test
    public void passwordFieldFormat() {
        // input given string into password (arg1)
        // get password field text and assert with expected (arg2)
    }

    //Should be tested on different browsers (mobile web?)
    //Compatibility testing

    //Check that the form matches the design
    //UI Testing

    //as an extra

    //SQL Injection
    //Security Testing
    @Test
    public void sqlInjection() {
        //separated tests
        //1. phone field
        //2. pass field
    }

    //XSS Injection
    @Test
    public void xssInjection() {
        //separated tests
        //1. phone field
        //2. pass field
    }
}
