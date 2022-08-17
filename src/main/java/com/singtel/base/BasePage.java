package com.singtel.base;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	protected WebDriver driver;
	protected JavascriptExecutor javascriptExecutor;
	protected Actions actions;

	public BasePage() {
		this.driver = BaseWebDriver.driver;
		this.javascriptExecutor = BaseWebDriver.javascriptExecutor;
		this.actions = BaseWebDriver.actions;
	}


	private void waitUntilCondition(ExpectedCondition<?> condition, String timeoutMessage, long timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.withMessage(timeoutMessage);
		wait.until(condition);
	}

	public void waitForLoading(int timeout){
		ExpectedCondition<Object> condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
		waitUntilCondition(condition, String.format("Page didn't load after [%s] seconds.",timeout), timeout);
	}

	public void waitForElementToBeDisplayed(int timeout, WebElement webElement, String webElementName){
		ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
		waitUntilCondition(condition, String.format("[%s] wasn't displayed after [%s] seconds.", webElementName,timeout),timeout);
	}

	public void waitForPresenceOfElements(int timeout, By elementLocator, String elementName){
		ExpectedCondition<List<WebElement>> condition = ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator);
		waitUntilCondition(condition, String.format("[%s] elements were not displayed after [%s] seconds.", elementName,timeout), timeout);
	}
}
