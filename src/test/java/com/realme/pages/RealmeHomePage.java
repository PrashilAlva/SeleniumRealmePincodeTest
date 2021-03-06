package com.realme.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RealmeHomePage {
	private By closePopupButton=By.xpath("/html/body/div[1]/div/img[1]");
	private By getSearchFieldButton=By.xpath("/html/body/header/div[2]/div[1]/a[1]/span");
	private By searchField=By.xpath("//*[@id=\"search-bar\"]/div/div[1]/input");
	private By searchButton=By.xpath("/html/body/header/div[2]/div[2]/div/div[1]/i[2]");
	
	private WebDriver driver;
	
	public RealmeHomePage(WebDriver driver){
		this.driver=driver;
	}
	
	public void closePopup() {
		driver.findElement(closePopupButton).click();
	}
	
	public void getSearchField() {
		driver.findElement(getSearchFieldButton).click();
	}
	
	public void enterSearchValue(String value) {
		driver.findElement(searchField).sendKeys(value);
	}
	
	public void performSearch() {
		driver.findElement(searchButton).click();
	}

}
