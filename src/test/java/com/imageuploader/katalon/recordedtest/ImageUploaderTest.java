package com.imageuploader.katalon.recordedtest;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImageUploaderTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase() throws Exception {
    driver.get("https://imageuploader2020.herokuapp.com/login");
    driver.findElement(By.name("uname")).click();
    driver.findElement(By.name("uname")).clear();
    driver.findElement(By.name("uname")).sendKeys("prashil");
    driver.findElement(By.name("pword")).click();
    driver.findElement(By.name("pword")).clear();
    driver.findElement(By.name("pword")).sendKeys("secret");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    Thread.sleep(5000);
    driver.findElement(By.name("fileName")).click();
    Thread.sleep(5000);
    driver.findElement(By.name("fileName")).clear();
    driver.findElement(By.name("fileName")).sendKeys("\\home\\prashilalva\\Downloads\\Prashil C Alva.jpg");
    driver.findElement(By.xpath("//input[@value='Upload']")).click();
    driver.findElement(By.xpath("//h1[2]")).click();
    driver.findElement(By.xpath("//h1[2]")).click();
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
