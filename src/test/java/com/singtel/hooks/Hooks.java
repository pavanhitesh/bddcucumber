package com.singtel.hooks;


import com.singtel.base.BaseWebDriver;
import com.singtel.util.ScreenShotUtil;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	
	@Before
	public void setupWebDriver() throws Exception {
		BaseWebDriver.setWebDriver();
	}
	

    @AfterStep
    public void beforeEachStep(Scenario sc){
        if(sc.isFailed()) {
            ScreenShotUtil.takeScreenshot();
        }
    }

}
