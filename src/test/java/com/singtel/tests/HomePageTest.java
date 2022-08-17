package com.singtel.tests;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;





@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/com/automatedtest/homePage.feature"},
        strict = false, plugin = {"pretty",
        "json:target/cucumber_json_reports/home-page.json",
        "html:target/home-page-html"},
        glue = {"com.singtel.steps","com.singtel.hooks"},dryRun = false)
public class HomePageTest {
	
}

