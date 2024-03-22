package com.vytrack.stepdefs;

import static com.vytrack.utils.Driver.*;

import com.vytrack.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.time.Duration;

import static com.vytrack.utils.JsonReader.getSingleString;

public class Hooks {
    @Before
    public void setupScenario(Scenario scenario){
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(getSingleString("url"));
    }

    @After
    public void tearDownScenario(Scenario scenario) {
//        if (scenario.isFailed()) {
//            // take screenshot using selenium
//            byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
//            // attach to report
//            scenario.attach(screenshot, "image/png", scenario.getName());
//        }
        Driver.closeDriver();
    }

    @AfterStep
    public static void takeScreenshot(Scenario scenario){
        //attach screenshot
        final byte[] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
}
