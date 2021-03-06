package com.realme.testing.firefox;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleSearchTest {
	
	WebDriver driver;
	
	@BeforeTest
	public void setupTest() {
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void test() {
		
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Prashil Alva linkedin");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@AfterTest
	public void closeTest() {
		driver.close();
		driver.quit();
	}

}
