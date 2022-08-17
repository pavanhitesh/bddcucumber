package com.singtel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.singtel.base.BasePage;


import java.util.List;
import java.util.stream.Collectors;


public class HomePage extends BasePage {

	private static final String HOME_PAGE_URL = "http://todomvc.com/examples/vue/";

	@FindBy(xpath = "//li[@class='todo']")
	private List<WebElement> todoRows;

	@FindBy(xpath = "//input[@class='new-todo']")
	private WebElement searchInput;

	@FindBy(xpath = "//button[@class='destroy']")
	private List<WebElement> removedItems;

	@FindBy(xpath = "//li[@class='todo']")
	private List<WebElement> availableItems;

	@FindBy(xpath = "//li[@class='todo']//label")
	private List<WebElement> existingItemsByName;

	@FindBy(xpath = "//li[@class='todo completed']//label")
	private List<WebElement> doneItems;
	
	@FindBy(xpath = "//li[contains(@class,'todo')]")
	private List<WebElement> allItems;

	@FindBy(xpath = "//footer//following::span[@class='todo-count']")
	public WebElement toDoCount;

	@FindBy(xpath = "//ul[@class='filters']//a[text()='All']")
	private WebElement filterAll;

	@FindBy(xpath = "//ul[@class='filters']//a[text()='Active']")
	private WebElement filterActive;


	@FindBy(xpath = "//ul[@class='filters']//a[text()='Completed']")
	private WebElement filterCompleted;

	@FindBy(xpath = "//button[@class='clear-completed']")
	private WebElement clearCompleted;


	public HomePage() throws Exception {
		PageFactory.initElements(driver, this);
	}

	public void goToHomePage() {
		driver.get(HOME_PAGE_URL);
		waitForElementToBeDisplayed(5, this.searchInput, "searchBox");
	}

	public void addItem(int itemCount) {
		for (int i = 0; i < itemCount; i++) {
			this.searchInput.sendKeys(String.format("testItem %s", i));
			this.searchInput.sendKeys(Keys.ENTER);
		}
	}

	public void addItem(String itemName) {
		this.searchInput.sendKeys(itemName);
		this.searchInput.sendKeys(Keys.ENTER);
	}

	public void removeItem(int itemCount) {
		for (int i = 0; i < itemCount; i++) {
			WebElement todoRow = todoRows.get(i);
			WebElement closeBtn = todoRow.findElement(By.xpath("//*[@class='destroy']"));
			javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", todoRow);
			actions.moveToElement(todoRow).build().perform();
			waitForLoading(1);
			javascriptExecutor.executeScript("arguments[0].click()", closeBtn);
		}
	}
	
	public void markComplete(int itemCount) {
		int count = 0;
		for(WebElement ele: existingItemsByName) {
			checkDoneItems(ele.getText());
			count++;
			if(count ==itemCount)
				break;
		}
	}

	public void removeItem(String itemName) {
		WebElement removeItem = driver.findElement(By.xpath(String.format("//li[@class='todo']//label[text()='%s']//parent::div//button[@class='destroy']", itemName)));
		javascriptExecutor.executeScript("arguments[0].click()", removeItem);
	}


	public void editItems(String existingToItemName, String newToDoItemName) {
		WebElement toDoItem = driver.findElement(By.xpath(String.format("//li[@class='todo']//label[text()='%s']", existingToItemName)));
		javascriptExecutor.executeScript("arguments[0].innerText='" + newToDoItemName + "'", toDoItem);

	}

	public List<String> getToDoItems() {
		return existingItemsByName.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public List<String> getDoneItems() {
		return doneItems.stream().map(WebElement::getText).collect(Collectors.toList());
	}

	public Integer getToDoItemCount() {
		return availableItems.size();
	}
	
	public Integer getAllItemCount() {
		return allItems.size();
	}
	
	public void clickAllFilter() {
		filterAll.click();
		waitForLoading(1);
	}
	
	public void clickActiveFilter() {
		filterActive.click();
		waitForLoading(1);
	}
	
	public void clickCompletedFilter() {
		filterCompleted.click();
		waitForLoading(1);
	}

	public void checkDoneItems(String doneItemName) {
		WebElement checkbox = driver.findElement(By.xpath(String.format("//li[@class='todo']//label[text()='%s']//parent::div/input[@type='checkbox']", doneItemName)));
		javascriptExecutor.executeScript("arguments[0].click()", checkbox);
	}
}
