package com.realme.testing.firefox;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.realme.configurations.TestConfigurations;
import com.realme.pages.RealmeHomePage;
import com.realme.pages.RealmeProductPage;
import com.realme.pages.RealmeResultPage;
import com.realme.utils.TestDataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RealmeSearchTest {
	// Declare Driver Object
	private WebDriver driver;

	// Declare DataProvider Object
	private TestDataProvider provider;

	// Declare POM (Page Object Model) Objects
	private RealmeHomePage homePageObj;
	private RealmeResultPage resultPageObj;
	private RealmeProductPage productPageObj;

	// Declare Extent Reports Objects
	private ExtentReports extent;
	private ExtentSparkReporter resultLog;
	private ExtentTest test;

	// Declare JsExecutor Object
	private JavascriptExecutor jsObj;

	// Declare Properties Object
	private TestConfigurations confObj;

	// Declare log4j Objects
	private Logger testLogger;

	String browseer;

	// Setup DataProvider function that provides data to test
	@DataProvider(name = "testDataProvider")
	public Object[][] testDataProvider() {
		provider = new TestDataProvider();
		return provider.dataProvider();
	}

	// Setup Preliminaries for test
	@Parameters({ "OS", "browser" })
	@BeforeTest
	public void setupDriver(String OS, String browser) {

		// Setup Extent Reports
		extent = new ExtentReports();
		resultLog = new ExtentSparkReporter("ExtentReports/log.html");

		// Setup Log4J
		testLogger = LogManager.getLogger(RealmeSearchTest.class.getName());

		// Initialize Extent Reports
		extent.attachReporter(resultLog);

		browseer = browser;
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // open Browser in maximized mode
		options.addArguments("disable-infobars"); // disabling infobars
		options.addArguments("--disable-extensions"); // disabling extensions
		options.addArguments("--disable-gpu"); // applicable to windows os only
		options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
		options.addArguments("--no-sandbox"); // Bypass OS security model

		// Setup WebDriverManager for Chrome
		WebDriverManager.chromedriver().setup();

		// Initialize Driver
		driver = new ChromeDriver(options);
		// Prelims for Testing
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);

		// Initialize POM Objects
		homePageObj = new RealmeHomePage(driver);
		resultPageObj = new RealmeResultPage(driver);
		productPageObj = new RealmeProductPage(driver);

		// Set System Information
		extent.setSystemInfo("OS", OS);
		extent.setSystemInfo("Browser", browser);

		// Initialize Properties Object
		confObj = new TestConfigurations();

		// Initialize JsExecutor Object
		jsObj = (JavascriptExecutor) driver;

		// Create a test with Test Name and its description
		test = extent.createTest("Realme Pincode functionality Verification Test",
				"This test is used to check whether the Pincode functionality of the Realme website works as per the expectations.");

		test.info("Preliminary Configurations for Test has been Setup");
		testLogger.info("Preminlinary Configurations done...");

	}

	@Test(dataProvider = "testDataProvider", groups = { "regression1" }, priority = 0)
	public void performTest(int pinCode, String probableResult) {
		try {

			test.info("Test has been Started for pin:" + pinCode);
			testLogger.info("Test started for pin:" + pinCode);

			// Get the Website
			driver.get(confObj.getUrl());
			test.info("Realme home page loaded Succesfully",
					MediaEntityBuilder.createScreenCaptureFromPath("screenshot.png").build());
			testLogger.info("Realme home page loaded");

			// Perform Stated Operations
//			homePageObj.closePopup();
//			test.info("Popup has been closed");
//			testLogger.info("Popup Closed");

			homePageObj.getSearchField();
			test.info("Search Field has been Obtained",
					MediaEntityBuilder.createScreenCaptureFromPath("screenshot1.png").build());
			testLogger.info("Search field obtained");

			Thread.sleep(2000);
			test.info("The thread has been put into sleep for 2 seconds");
			testLogger.info("Thread Sleep");

			homePageObj.enterSearchValue(confObj.getSearchTerm());
			test.info("Realme X50 Pro has been Entered in the Search Field");
			testLogger.info("Search field filled");

			homePageObj.performSearch();
			test.info("Search Operation Initiated");
			testLogger.info("Search Started");

			Thread.sleep(2000);
			test.info("The thread has been put into sleep for 2 seconds");
			testLogger.info("Thread Sleep");

			test.info("Search Results page has been loaded",
					MediaEntityBuilder.createScreenCaptureFromPath("screenshot2.png").build());
			testLogger.info("Search Result obtained");

			resultPageObj.selectProduct();
			test.info("Product has been Selected");
			testLogger.info("Product Selected");

			test.info("Product page loaded successfully");
			testLogger.info("Product page loaded");

			// Scroll the page by 600 px y axis
			jsObj.executeScript("window.scrollBy(0,600)");

			productPageObj.enterPincode(String.valueOf(pinCode));
			test.info("Pincode has been entered into the Pincode Verification field");
			testLogger.info("Pincode entered");

			productPageObj.verifyPincode();
			test.info("Pincode Verification has been initiated");
			testLogger.info("Pincode verification started");

			Thread.sleep(3000);
			test.info("Thread has been put into sleep for 3 seconds");
			testLogger.info("Thread Sleep");

			// Get the Actual Result
			String actualResult = productPageObj.getPincodeVerificationResult();

			// Set the Expected result value
			String expectedResult = probableResult;

			// Compare the Results and Print Accordingly
			Assert.assertEquals(expectedResult, actualResult, "Exception Occured");
			test.pass("Pincode Verification Performed Successfully for Pin:" + pinCode,
					MediaEntityBuilder.createScreenCaptureFromPath("screenshot3.png").build());
			testLogger.info("Test Successfull for pin:" + pinCode);

		} catch (Throwable e) {
			if (probableResult.equals("Delivery Not Available")) {
				test.pass("Pincode Verification failed as excepted for pincode:" + pinCode);
				testLogger.info("Test passed");
			} else {
				test.fail("Pincode Verification failed:" + pinCode);
				testLogger.error("Test failed for pin:" + pinCode);
			}

		}
		driver.manage().deleteAllCookies();
		test.info("Test Completed for pin:" + pinCode);
		testLogger.info("Test Complete for pin:" + pinCode);
	}

	@Test(dataProvider = "testDataProvider", groups = { "regression1", "smoke" }, priority = 1)
	public void dummyTest1(int pinCode, String probableResult) {
		System.out.println("Inside Test1");
		System.out.println(pinCode + "$" + probableResult);
	}

	@Test(dataProvider = "testDataProvider", groups = { "smoke" })
	public void dummyTest2(int pinCode, String probableResult) {
		System.out.println("Inside Test2");
		System.out.println(pinCode + "||" + probableResult);
	}

	@AfterTest
	public void quitDriver() {

		// Flush the logs into html file
		extent.flush();

		// Close the driver
		driver.close();

		// Quit the Driver
		driver.quit();
	}

}
