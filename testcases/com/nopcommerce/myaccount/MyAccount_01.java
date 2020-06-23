package com.nopcommerce.myaccount;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import browsers.BrowserDriverFactory;
import browsers.DriverManager;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.CustomerAddressesPageObject;
import pageObjects.CustomerInfosPageObject;
import pageObjects.HomePageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;

public class MyAccount_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private RegisterPageObject registerObject;
	private HomePageObject homeObject;
	private MyAccountPageObject accountObject;
	private CustomerInfosPageObject customerInfoObject;
	private CustomerAddressesPageObject customerAddressObject;
	private String email;
	private String updatedGender = "female";
	private String updatedFirstName = "Automation";
	private String updatedLastName = "FC";
	private String[] updateDOB = { "January", "1", "1999" };
	private String updatedCompanyName = "Automation FC";
	private String updatedEmail;
	private String actualValue;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driverManager = BrowserDriverFactory.getBrowserDriver(browserName);
		driver = driverManager.getDriver();
		startObject = PageFactoryManager.getStartPageObject(driver);
		startObject.navigateToPage_HeaderLink(driver, "register");
		registerObject = PageFactoryManager.getRegisterPageObject(driver);

		// register + login success
		email = createRandomEmail(GlobalConstants.fistName, GlobalConstants.lastName);
		registerObject.inputFirstName(GlobalConstants.fistName);
		registerObject.inputLastName(GlobalConstants.lastName);
		registerObject.inputEmail(email);
		registerObject.inputPasword("123456");
		registerObject.inputConfirmPassword("123456");
		registerObject.clickRegisterButton();
		homeObject = PageFactoryManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_updateCustomerInfos() {
		// navigate to My Account
		homeObject.navigateToPage_HeaderLink(driver, "account");
		customerInfoObject = PageFactoryManager.getCustomerInfosPageObject(driver);

		// Update infos
		customerInfoObject.updateGender(updatedGender);
		customerInfoObject.updateFirstName(updatedFirstName);
		customerInfoObject.updateLastName(updatedLastName);
		customerInfoObject.updateDateOfBirth(updateDOB[0], updateDOB[1], updateDOB[2]);
		updatedEmail = createRandomEmail("automationfc", "vn");
		customerInfoObject.updateEmail(updatedEmail);
		customerInfoObject.updateCompanyName(updatedCompanyName);

		// save updated infos
		customerInfoObject.clickSaveButton();

		// Verify infos after updated
		actualValue = customerInfoObject.getValueOfField("female");
		Assert.assertEquals(actualValue, updatedGender);

		actualValue = customerInfoObject.getValueOfField("first name");
		Assert.assertEquals(actualValue, updatedFirstName);

		actualValue = customerInfoObject.getValueOfField("last name");
		verifyEquals(actualValue, updatedLastName);

		actualValue = customerInfoObject.getValueOfField("birth day");
		verifyEquals(actualValue, updateDOB[1]);

		actualValue = customerInfoObject.getValueOfField("birth month");
		verifyEquals(actualValue, updateDOB[0]);

		actualValue = customerInfoObject.getValueOfField("birth year");
		verifyEquals(actualValue, updateDOB[2]);

		actualValue = customerInfoObject.getValueOfField("email");
		verifyEquals(actualValue, updatedEmail);

		actualValue = customerInfoObject.getValueOfField("company");
		verifyEquals(actualValue, updatedCompanyName);
	}

	@Test
	public void TC_02_addAddress() {
		// navigate to Customer Addresses
		homeObject.navigateToPage_ContentList(driver, "/customer/addresses");
		customerAddressObject = PageFactoryManager.getCustomerAddressesPageObject(driver);

		//click add new
		customerAddressObject.clickAddNewButton();
		
		//enter new address
		customerAddressObject.inputNewInfos("FirstName", "Automation");
		customerAddressObject.inputNewInfos("LastName", "FC");
		customerAddressObject.inputNewInfos("Email", "automationfc.vn@gmail.com");
		customerAddressObject.inputNewInfos("Company", "Automation FC");
		
		customerAddressObject.selectNewInfos("CountryId", "Viet Nam");
		customerAddressObject.selectNewInfos("StateProvinceId", "Other");
				
		customerAddressObject.inputNewInfos("City", "Da Nang");
		customerAddressObject.inputNewInfos("Address1", "123/01 Le Lai");
		customerAddressObject.inputNewInfos("Address2", "234/05 Hai Phong");
		customerAddressObject.inputNewInfos("ZipPostalCode", "550000");
		customerAddressObject.inputNewInfos("PhoneNumber", "0123456789");
		customerAddressObject.inputNewInfos("FaxNumber", "0987654321");
		
		//click save
		customerAddressObject.clickSaveButton();
		
		//Verify address
		actualValue = customerAddressObject.getCustomerName("Automation FC", "name");
		verifyTrue(actualValue.contains("Automation FC"));
				
		actualValue = customerAddressObject.getCustomerName("Automation FC", "email");
		verifyTrue(actualValue.contains("automationfc.vn@gmail.com"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "phone");
		verifyTrue(actualValue.contains("0123456789"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "fax");
		verifyTrue(actualValue.contains("0987654321"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "company");
		verifyTrue(actualValue.contains("Automation FC"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "address1");
		verifyTrue(actualValue.contains("123/01 Le Lai"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "address2");
		verifyTrue(actualValue.contains("234/05 Hai Phong"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "city-state-zip");
		verifyTrue(actualValue.contains("Da Nang, 550000"));
		
		actualValue = customerAddressObject.getCustomerName("Automation FC", "country");
		verifyTrue(actualValue.contains("Viet Nam"));
		
		
		
	}

	@AfterClass
	public void afterClass() {
	}

}
