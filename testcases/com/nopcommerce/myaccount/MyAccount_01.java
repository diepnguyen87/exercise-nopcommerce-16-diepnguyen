package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.GlobalConstants;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;

public class MyAccount_01 {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private RegisterPageObject registerObject;
	private HomePageObject homeObject;
	private MyAccountPageObject accountObject;
	private String email;
	private String updatedFirstName="Automation";
	private String updatedLastName="FC";
	private String companyName = "Automation FC";

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
		registerObject = startObject.clickRegisterLink();

		// register + login success
		email = registerObject.createRandomEmail(GlobalConstants.fistName, GlobalConstants.lastName);
		registerObject.inputFirstName(GlobalConstants.fistName);
		registerObject.inputLastName(GlobalConstants.lastName);
		registerObject.inputEmail(email);
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword("123456");
		registerObject.clickRegisterButton();
		homeObject = PageFactoryManager.getHomePageObject(driver);

		// homepage
		accountObject = homeObject.clickMyAccountLink();
		accountObject.selectGender("female");
		accountObject.updateFirstName(updatedFirstName);
		accountObject.updateLastName(updatedLastName);
		accountObject.selectDateOfBirth("January", "1", "1999");
		accountObject.updateEmail("automationfc.vn@gmail.com");
		accountObject.updateCompanyName(companyName);
	}

	@Test
	public void TC_01_updateCustomerInfos() {
		
	}

	@AfterClass
	public void afterClass() {
	}

}
