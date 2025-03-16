package com.assignment.pages;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.assignment.utils.JsExecutorUtil.scrollToElement;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class UpcomingEventPage extends BasePage {

    static {
        url = "/sportsbook/upcoming";
    }

    public UpcomingEventPage() {
        sortByTime.should(visible);
    }

    private static final Logger logger = LoggerFactory.getLogger(UpcomingEventPage.class);
    private final SelenideElement loginButton = $("user-menu-login");
    private final SelenideElement sortByTime = $x("//div[@class='sort']//ul");
    private final SelenideElement showMore = $x("//*[@class='show-more']");
    private final ElementsCollection gameIds = $$x("//*[@class='event-gameId']");

    private final SelenideElement loggedInLogo = $("by-some-locator");//mock

    @Step("open login page")
    public LoginPage openLoginPage() {
        logger.info("open login page");
        loginButton
                .should(visible)
                .click();
        return new LoginPage();
    }

    @Step("logged in logo should be visible: {shouldBeLogged}")
    public void checkIsLoggedIn(boolean shouldBeLogged) {
        loggedInLogo.shouldBe(shouldBeLogged ? visible : not(exist));
    }

    @Step("select sort time '{timeSort}'")
    public UpcomingEventPage pickSortByTime(TimeSort timeSort) {
        sortByTime
                .should(enabled)
                .click();
        sortByTime
                .find(By.xpath(String.format(".//*[text()='%s']", timeSort.buttonText)))
                .click();
        return this;
    }

    /**
     * Presses "show more" button till all events are loaded
     * Max attempts to load all elements is 15
     */
    @Step("load all events")
    public UpcomingEventPage loadAllEvents() {
        int maxAttempts = 15; // Ограничение на количество итераций
        int attempts = 0;
        while (showMore.exists() && attempts < maxAttempts) {
            int countBefore = gameIds.size();
            scrollToElement(showMore);
            showMore.click();
            gameIds.shouldHave(CollectionCondition.sizeGreaterThan(countBefore));
            attempts++;
        }
        Assert.assertFalse(showMore.isDisplayed(),"Couldn't load all events within 15 attempts");
        scrollToElement(sortByTime);
        return this;
    }

    public HashMap<String, List<String>> getEventRowsWithOdds() {
        ElementsCollection elements = $$x("//*[@class='event-row']");
        return (HashMap<String, List<String>>) elements.stream()
                .collect(Collectors.toMap(
                        el -> el.$$x(".//following-sibling::tr//span[@class='event-gameId']").first().text(), // Получаем id элемента
                        el -> el.$$x(".//*[@class='odd-content notranslate']").texts()
                ));
    }


    public enum TimeSort {
        //better to receive text from dictionary
        BY_3_HOURS("Next 3 hours");

        public final String buttonText;

        TimeSort(String buttonText) {
            this.buttonText = buttonText;
        }

        @Override
        public String toString() {
            return buttonText;
        }
    }

}
