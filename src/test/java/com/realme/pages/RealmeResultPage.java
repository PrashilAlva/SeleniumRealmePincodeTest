package com.realme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RealmeResultPage {

	private WebDriver driver;

	public RealmeResultPage(WebDriver driver) {
		this.driver = driver;
	}

	private By product = By.xpath("//*[@id=\"search-result-products\"]/li[1]/a/div[2]/label");

	public void selectProduct() {
		driver.findElement(product).click();
	}
}
