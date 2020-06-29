package com.nopcommerce.wishlist;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class NewTest {

	private WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01() throws InterruptedException {
		jsExecutor.executeScript("window.scrollTo(0,document.body.scrollHeight);");
		Thread.sleep(5*1000);
		driver.findElement(By.xpath("//input[@class='button-1 search-box-button']")).click();
	}

	@AfterClass
	public void afterClass() {
	}

}
