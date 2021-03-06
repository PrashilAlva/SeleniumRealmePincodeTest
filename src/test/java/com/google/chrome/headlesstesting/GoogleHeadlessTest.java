package com.google.chrome.headlesstesting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleHeadlessTest {

	private WebDriver driver;
	private ChromeOptions options;
	private String actualResult;
	private String expectedResult;

	@BeforeTest
	public void setUp() {
//		options.addArguments("--headless");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

		System.out.println("Test Started");
	}

	@Test
	public void headlessTest() {
		try {
			driver.get("https://google.com");
			driver.findElement(By.name("q")).sendKeys("Coronavirus India");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			expectedResult = "Coronavirus India - Google Search";
			actualResult = driver.getTitle();
			Assert.assertEquals(expectedResult, actualResult);
			System.out.println("Test Passed");
		} catch (Throwable e) {
			System.out.println("Test failed");
		}
	}

	@AfterTest
	public void teardown() {
		driver.close();
		driver.quit();
		System.out.println("Test failed");
	}

}
