package com.singtel.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class BaseWebDriver {

	public static WebDriver driver = null;
	public static JavascriptExecutor javascriptExecutor;
	public static Actions actions;
	public static String baseLocation = System.getProperty("user.dir");



	public static void setWebDriver() throws Exception {

		if(driver==null) {
			String browser = System.getProperty("browser");
			browser = browser == null ? "chrome":browser;
			switch (browser) {
			case "chrome":
				if(System.getProperty("webdriver.chrome.driver") ==null) {
					System.setProperty("webdriver.chrome.driver",baseLocation+"//src//test//resources//executables//chromedriver.exe");
				}
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				break;
			case "firefox":
				if(System.getProperty("webdriver.gecko.driver") ==null) {
					System.setProperty("webdriver.gecko.driver",baseLocation+"//src//test//resources//executables//geckodriver.exe");
				}
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				break;
			default:
				throw new IllegalArgumentException(String.format("Browser [%s] isn't supported.", browser));
			}
			javascriptExecutor = ((JavascriptExecutor) driver);
			actions = new Actions(driver);
		}
	}
}
