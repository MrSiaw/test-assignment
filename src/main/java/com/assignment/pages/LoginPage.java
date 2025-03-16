package com.assignment.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.assignment.config.Config.DEFAULT_PHONE_CODE;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    static {
        url = "login";
    }

    private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);
    private final SelenideElement phoneInput = $(By.id("phoneInput"));
    private final SelenideElement phoneCodeDropdown = $x("//*[contains(@class,'form-phone')]//*[@class='dropdown-label']");
    private final SelenideElement passwordInput = $(By.id("password"));
    private final SelenideElement loginSubmitButton = $(By.id("buttonLoginSubmitLabel"));
    private final SelenideElement errorMessage = $x("//div[@au-validation]/div[@class='au-m-val' and string-length() > 0]");
    private final SelenideElement showPassword = $("au-i-filled-eye-cancel");

    //it may be redundant to write a getter for each element and  it is easier to make the element public, which won't be very similar to encapsulation.
    //so far only 2 new methods are needed.
    public String getPhoneText() {
        return phoneInput.getText();
    }

    public String getPasswordInputText() {
        if (showPassword.isDisplayed()) {
            showPassword.click();
        }
        return passwordInput.getText();
    }

    /**
     * Fills the login form with given number and pass and clicks submit
     * Checks that phone code equals to config
     *
     * @param phone    -   user's phone number without code
     * @param password -   user's password
     * @return -   login page
     */
    @Step("Try to login with phone '{phone}'")
    public LoginPage login(String phone, String password) {
        logger.info("Try to login with phone: {}", phone);
        phoneInput.setValue(phone);
        //if it's fixed for the whole run, we can use it from config
        phoneCodeDropdown.should(text(DEFAULT_PHONE_CODE));
        //if not, pass User-class to method with phone number and pick it
        /*phoneCodeDropdown.click();
        $$x("//div[contains(@class,'form-phone')]//ul[@au-dropdown and contains(@class,multiple)]//span")
                .find(text(user.getPhoneCode()))
                .click();*/
        passwordInput.setValue(password);
        loginSubmitButton.click();
        return this;
    }

    /**
     * Make sure that the error message appears and equals to the expected text.
     *
     * @param expected - expected text
     */
    @Step("Check that error message '{expected}' is presented")
    public void checkErrorMessageText(String expected) {
        errorMessage
                .shouldBe(visible)
                .should(text(expected));
    }

}
