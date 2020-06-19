package com.nopcommerce.register;

import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageUI.RegisterPageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Register_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private HomePageObject homeObject;
	private RegisterPageObject registerObject;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		homeObject = PageFactoryManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_registerWithEmptyData() {
		registerObject = homeObject.clickRegisterLink();
		registerObject.clickRegisterButton();

		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.FIRSTNAME_ERROR_MESSAGE, "First name is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.LASTNAME_ERROR_MESSAGE, "Last name is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.EMAIL_ERROR_MESSAGE, "Email is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.PASSWORD_ERROR_MESSAGE, "Password is required."));
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.CONFIRMPASSWORD_ERROR_MESSAGE, "Password is required."));
	}

	@Test
	public void TC_02_registerWithInvalidEmail() {
		registerObject.inputFirstName("Diep");
		registerObject.inputLastName("Nguyen");
		registerObject.inputEmail("123@123.123");
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword(("123456"))
		
		registerObject.clickRegisterButton();
		Assert.assertTrue(registerObject.isErrorMessageDisplayed(RegisterPageUI.EMAIL_ERROR_MESSAGE, "Email is required."));
		
		
	}
	
	@AfterClass
	public void afterClass() {
	}

}
