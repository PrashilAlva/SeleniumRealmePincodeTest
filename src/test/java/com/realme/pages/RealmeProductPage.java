package com.realme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RealmeProductPage {

	private WebDriver driver;

	public RealmeProductPage(WebDriver driver) {
		this.driver = driver;
	}

	private By pincodeField = By.xpath("/html/body/div[2]/div[2]/div/div[2]/div[9]/div[2]/input");
	private By pincodeVerifyButton = By.xpath("/html/body/div[2]/div[2]/div/div[2]/div[9]/div[2]/a");
	private By pincodeVerificationResult = By.xpath("//*[@id=\"app\"]/div[2]/div/div[2]/div[9]/div[2]/div/p[2]/a/span");

	public void enterPincode(String pincode) {
		driver.findElement(pincodeField).sendKeys(pincode);
	}

	public void verifyPincode() {
		driver.findElement(pincodeVerifyButton).click();
	}

	public String getPincodeVerificationResult() {
		return driver.findElement(pincodeVerificationResult).getText();
	}

}
