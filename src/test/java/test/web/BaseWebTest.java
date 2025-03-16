package test.web;

import com.assignment.config.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class BaseWebTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseWebTest.class);

    @BeforeSuite
    public void setUpDriver() {
        logger.info("Configuring web driver");
        //should be from config
        Configuration.browser = "chrome";
        Configuration.baseUrl = Config.BASE_URL;
        Configuration.pageLoadTimeout = TimeUnit.SECONDS.toMillis(10);
        Configuration.timeout = TimeUnit.SECONDS.toMillis(15);
        Configuration.pollingInterval = 200;
    }

    @BeforeMethod
    public void setUp() {
        Selenide.open(Config.BASE_URL);
        getWebDriver().manage().window().maximize();
    }

    @AfterMethod
    public void closeDriver() {
        Selenide.closeWebDriver();
    }
}
