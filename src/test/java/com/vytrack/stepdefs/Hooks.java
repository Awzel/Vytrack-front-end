package com.vytrack.stepdefs;

import com.vytrack.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.time.Duration;

import static com.vytrack.utils.JsonReader.getSingleString;

public class Hooks {
    @Before
    public void setupScenario(Scenario scenario){
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().get(getSingleString("url"));
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
}
