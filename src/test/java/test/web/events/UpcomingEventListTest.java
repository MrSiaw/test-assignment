package test.web.events;

import com.assignment.pages.UpcomingEventPage;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import test.web.BaseWebTest;

import java.util.List;
import java.util.Map;

@Epic("events")
@Story("upcoming")
public class UpcomingEventListTest extends BaseWebTest {

    private static final Logger logger = LoggerFactory.getLogger(UpcomingEventListTest.class);

    @Test(testName = "filter upcoming events with odds >= 1.5 and <=3.34")
    public void upcomingEventsFilter() {
        UpcomingEventPage upcomingEventPage = new UpcomingEventPage();
        upcomingEventPage.open();
        Map <String, List<Double>> str = upcomingEventPage
                .pickSortByTime(UpcomingEventPage.TimeSort.BY_3_HOURS)
                .loadAllEvents()
                .getEventRowsWithOdds();
        logger.info("before");
        str.forEach((key, value) -> logger.info("{} - {}", key, value));
        logger.info("after");
        //this could be a separate method, some kind of a OddsUtil
        //it depends on the following tests, where it is better to apply this method.
        str
                .entrySet()
                .stream()
                .filter((entry) -> entry
                        .getValue()
                        .stream()
                        .allMatch(odd -> {
                            try {
                                return odd >= 1.50d && odd <= 3.34d;
                            } catch (NumberFormatException exception) {
                                throw new AssertionError("Can't convert given odd to double: " + odd);
                            }
                        }))
                .forEach(entry -> logger.info("{} - {}", entry.getKey(), entry.getValue()));
        //Now we have a list of events id's that matches given filter 1.5 >= x <= 3.34. We can use this id for REST API of SQL
        //For example, we can add a filter to search only for those rows where all 8 coefficients are available
        //Either the number of coefficients should be 5 >= odds_count <= 8
        //I don't get which assert we can add here, cause I don't know where I can get an expected
    }
}
