package com.realme.testing.title;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class RealmeTitleTest {
	
	WebDriver driver;
	
	@Test
	public void loadPage() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/driver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		driver.navigate().to("https://realme.com/in/");
		String expectedTitle="realme (India) - Dare to Leap";
		String actualTitle=driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		driver.quit();
	}

	public static void main(String[] args) {
		RealmeTitleTest obj=new RealmeTitleTest();
		obj.loadPage();
	}

}
