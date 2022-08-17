package com.singtel.hooks;

import io.cucumber.java.Scenario;
import io.cucumber.java.After;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.singtel.base.BaseWebDriver;

public class TearDown {

    private WebDriver driver;

    public TearDown() {
        this.driver = BaseWebDriver.driver;
    }

    @After
    public void screenShot(Scenario scenario){
        if(scenario.isFailed()){
           saveScreenshotsForScenario(scenario);
        }
        this.driver.quit();
        BaseWebDriver.driver = null;
    }

    @SuppressWarnings("deprecation")
	private void saveScreenshotsForScenario(final Scenario scenario) {

        final byte[] screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.BYTES);
        scenario.embed(screenshot, "image/png");
    }
}
