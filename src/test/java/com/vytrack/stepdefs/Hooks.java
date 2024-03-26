package com.vytrack.stepdefs;

import static com.vytrack.utils.Driver.*;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.aventstack.extentreports.service.ExtentService;
import com.vytrack.utils.BrowserUtil;
import com.vytrack.utils.Driver;
import com.vytrack.utils.GlobalData;
import io.cucumber.java.*;

import java.time.Duration;

import static com.vytrack.utils.JsonReader.getSingleString;

public class Hooks {

    GlobalData globalData;

    public Hooks(GlobalData globalData) {
        this.globalData = globalData;
    }

    @Before
    public void setupScenario(Scenario scenario){
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        getDriver().get(getSingleString("url"));
        ExtentCucumberAdapter.getCurrentStep().log(Status.INFO,"URL successfully launched");
        globalData.setScenarioName(scenario.getName());
    }

    @After
    public void tearDownScenario() {
        Driver.closeDriver();
    }

    @AfterStep
    public static void after_step(Scenario scenario){
        //attach screenshot
        if (scenario.isFailed()){
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL,MediaEntityBuilder.createScreenCaptureFromBase64String(BrowserUtil.takeScreenshotAsBase64()).build());
        }
    }

    @AfterAll
    public static void after_all(){
        ExtentService.getInstance().setSystemInfo("Device",System.getProperty("os.name"));
    }
}
