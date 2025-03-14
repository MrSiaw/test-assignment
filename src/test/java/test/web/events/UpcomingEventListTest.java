package test.web.events;

import com.assignment.pages.UpcomingEventPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import test.web.BaseWebTest;
import test.web.login.LoginTest;

import java.util.HashMap;
import java.util.List;

@Epic("events")
@Story("upcoming")
public class UpcomingEventListTest extends BaseWebTest {

    private static final Logger logger = LoggerFactory.getLogger(UpcomingEventListTest.class);

    @Test(testName = "")
    public void upcomingEventsList() {
        UpcomingEventPage upcomingEventPage = new UpcomingEventPage();
        upcomingEventPage.open();
        HashMap <String, List<String>> str = upcomingEventPage
                .pickSortByTime(UpcomingEventPage.TimeSort.BY_3_HOURS)
                .loadAllEvents()
                .getEventRowsWithOdds();
        logger.info("before");
        str.forEach((key, value) -> logger.info("{} - {}", key, value));
        logger.info("after");
        str
                .entrySet()
                .stream()
                .filter((entry) -> entry
                        .getValue()
                        .stream()
                        .allMatch(odd -> Double.parseDouble(odd) >= 1.50d && Double.parseDouble(odd) <= 3.34d))
                .forEach(entry -> logger.info("{} - {}", entry.getKey(), entry.getValue()));
    }
}
