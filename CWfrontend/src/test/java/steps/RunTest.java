package steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import helpers.DriverUtil;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

/**
 * Created by isaacthomas on 08/10/2018.
 */

    @RunWith(Cucumber.class)
    @CucumberOptions(format={"html:build/reports/functionalities","json:target/cucumber-report.json"},features={"src/test/resource/outline"},glue={"steps"},tags={"@cw"},strict=false)

    public class RunTest{
        public static WebDriver driver;
        @BeforeClass
        public static void setUp() {

            DriverUtil.setDriver();

        }
        @AfterClass

        public static void tearDown() throws Throwable {

            DriverUtil.closeDriver();

        }
    }

