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
public class LoginFormTest extends BaseWebTest {

    private static final Logger logger = LoggerFactory.getLogger(LoginFormTest.class);

    private static final String VALID_USER_PHONE = "962323422";
    private static final String INVALID_USER_PHONE = "962323422";
    private static final String VALID_USER_PASS = "valid_pass";
    private static final String ERROR_PHONE_OR_PASS_INVALID = "The Phone number or password entered is invalid";
    private static final String ERROR_EMPTY_PHONE = "Phone number is required";
    private static final String ERROR_EMPTY_PASS = "Password is required";

    /**
     * Open login page from upcoming events, fill phone and pass and press submit
     * Check that error with message appears
     *
     * @param phone                -   phone number
     * @param pass                 -   pass
     * @param expectedErrorMessage -    expected error message
     */
    private void failedLoginWithError(String phone, String pass, String expectedErrorMessage) {
        UpcomingEventPage upcomingEventPage = new UpcomingEventPage();
        upcomingEventPage.checkIsLoggedIn(false);
        upcomingEventPage
                .openLoginPage()
                .login(phone, pass)
                .checkErrorMessageText(expectedErrorMessage);
        upcomingEventPage.checkIsLoggedIn(false);
    }

    //Equivalence
    @Test(testName = "Successful login")
    //@AllureId("") should be some id
    public void successfulLogin() {
        UpcomingEventPage upcomingEventPage = new UpcomingEventPage();
        upcomingEventPage.checkIsLoggedIn(false);
        upcomingEventPage
                .openLoginPage()
                .login(VALID_USER_PHONE, VALID_USER_PASS);
        upcomingEventPage.checkIsLoggedIn(true);
    }

    //Equivalence
    @Test(testName = "Invalid Password")
    public void invalidPassword() {
        failedLoginWithError(VALID_USER_PHONE, VALID_USER_PASS, ERROR_PHONE_OR_PASS_INVALID);
    }

    //Equivalence
    @Test(testName = "Invalid user phone")
    public void invalidUserPhone() {
        failedLoginWithError(INVALID_USER_PHONE, VALID_USER_PASS, ERROR_PHONE_OR_PASS_INVALID);
    }

    //Equivalence
    @Test(testName = "Empty phone number")
    public void emptyPhoneNumber() {
        failedLoginWithError("", VALID_USER_PASS, ERROR_EMPTY_PHONE);
    }

    //Equivalence
    @Test(testName = "Empty password")
    public void emptyPassword() {
        failedLoginWithError(VALID_USER_PHONE, "", ERROR_EMPTY_PASS);
    }

    //State & Transition
    @Test
    public void loginButtonEnable() {
        //open login form
        //form are clear (expect for phone code)
        //login button should be disabled
        //fill the phone number with valid
        //login button should be disabled
        //fill the pass with valid
        //login button should be enabled
        //it looks enabled even with empty form, is ot okay?
    }

    //State & Transition
    @Test
    public void phoneCodeIsPreFilled() {
        //open login form
        //phone code is prefilled

        //still don't know where it comes fromm could add Equivalence test if we can change country and it affects phone code
        //vpn didn't help, I believe it depends on a brand, this specific brand is for Zambia
    }

    //Could be checks for login attempts, but couldn't find valid phone to find

    //Phone field validation
    //Boundary Value
    //I believe that in this case min == max, so let it be just X (numbers count)
    //data provider:
    // X-1; true
    // X; false
    // X+1; false
    @Test
    public void phoneFieldValidation() {
        //open login form
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
        //open login form
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
        //open login form
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
        //open login form
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
        //open login form
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

    //State & Transition?
    @Test
    public void filledFormRefresh() {
        //open the login form
        //fill the form with valid data
        //refresh the page
        //pass field should be empty
        //phone filed is empty, but it could be a good thing to keep the phone number after refresh (negotiable)
    }
}
