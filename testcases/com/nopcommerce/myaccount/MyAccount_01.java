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
import pageObjects.CustomerChangePasswordPageObject;
import pageObjects.CustomerInfosPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.MyAccountPageObject;
import pageObjects.PageFactoryManager;
import pageObjects.RegisterPageObject;
import pageObjects.StartPageObject;

public class MyAccount_01 extends AbstractTest {

	private WebDriver driver;
	private DriverManager driverManager;
	private StartPageObject startObject;
	private LoginPageObject loginObject;
	private RegisterPageObject registerObject;
	private HomePageObject homeObject;
	private MyAccountPageObject accountObject;
	private CustomerInfosPageObject customerInfoObject;
	private CustomerAddressesPageObject customerAddressObject;
	private CustomerChangePasswordPageObject changePasswordObject;

	private String email;
	private String newEmail;
	private String password = GlobalConstants.PASSWORD;
	private String newPassword = "654321";
	private String newGender = "female";
	private String newFirstName = "Automation";
	private String newLastName = "FC";
	private String[] newOB = { "January", "1", "1999" };
	private String newCompanyName = "Automation FC";
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
		email = createRandomEmail(GlobalConstants.FIRSTNAME, GlobalConstants.LASTNAME);
//		registerObject.inputFirstName(GlobalConstants.FIRSTNAME);
//		registerObject.inputLastName(GlobalConstants.LASTNAME);
//		registerObject.inputEmail(email);
//		registerObject.inputPasword("123456");
//		registerObject.inputConfirmPassword("123456");
//		registerObject.clickRegisterButton();
		registerObject.register(GlobalConstants.FIRSTNAME, GlobalConstants.LASTNAME, email, password);
		GlobalConstants.EMAIL = email;
		homeObject = PageFactoryManager.getHomePageObject(driver);
	}

	@Test
	public void TC_01_updateCustomerInfos() {
		// navigate to My Account
		homeObject.navigateToPage_HeaderLink(driver, "account");
		customerInfoObject = PageFactoryManager.getCustomerInfosPageObject(driver);

		// Update infos
		customerInfoObject.updateGender(newGender);
		customerInfoObject.updateFirstName(newFirstName);
		customerInfoObject.updateLastName(newLastName);
		customerInfoObject.updateDateOfBirth(newOB[0], newOB[1], newOB[2]);
		newEmail = createRandomEmail("automationfc", "vn");
		customerInfoObject.updateEmail(newEmail);
		customerInfoObject.updateCompanyName(newCompanyName);

		// save new infos
		customerInfoObject.clickSaveButton();
		GlobalConstants.EMAIL = newEmail;

		// Verify infos after new
		actualValue = customerInfoObject.getValueOfField("female");
		Assert.assertEquals(actualValue, newGender);

		actualValue = customerInfoObject.getValueOfField("first name");
		Assert.assertEquals(actualValue, newFirstName);

		actualValue = customerInfoObject.getValueOfField("last name");
		verifyEquals(actualValue, newLastName);

		actualValue = customerInfoObject.getValueOfField("birth day");
		verifyEquals(actualValue, newOB[1]);

		actualValue = customerInfoObject.getValueOfField("birth month");
		verifyEquals(actualValue, newOB[0]);

		actualValue = customerInfoObject.getValueOfField("birth year");
		verifyEquals(actualValue, newOB[2]);

		actualValue = customerInfoObject.getValueOfField("email");
		verifyEquals(actualValue, newEmail);

		actualValue = customerInfoObject.getValueOfField("company");
		verifyEquals(actualValue, newCompanyName);
	}

	@Test
	public void TC_02_addAddress() {
		// data
		String country = "Viet Nam";
		String state = "Other";
		String city = "Da Nang";
		String address1 = "123/01 Le Lai";
		String address2 = "234/05 Hai Phong";
		String zipcode = "550000";
		String phoneNumber = "0123456789";
		String faxNumber = "0987654321";

		// navigate to Customer Addresses
		homeObject.navigateToPage_ContentList(driver, "/customer/addresses");
		customerAddressObject = PageFactoryManager.getCustomerAddressesPageObject(driver);

		// click add new
		customerAddressObject.clickAddNewButton();

		// enter new address
		customerAddressObject.inputNewInfos("FirstName", newFirstName);
		customerAddressObject.inputNewInfos("LastName", newLastName);
		customerAddressObject.inputNewInfos("Email", newEmail);
		customerAddressObject.inputNewInfos("Company", newCompanyName);

		customerAddressObject.selectNewInfos("CountryId", country);
		customerAddressObject.selectNewInfos("StateProvinceId", state);

		customerAddressObject.inputNewInfos("City", city);
		customerAddressObject.inputNewInfos("Address1", address1);
		customerAddressObject.inputNewInfos("Address2", address2);
		customerAddressObject.inputNewInfos("ZipPostalCode", zipcode);
		customerAddressObject.inputNewInfos("PhoneNumber", phoneNumber);
		customerAddressObject.inputNewInfos("FaxNumber", faxNumber);

		// click save
		customerAddressObject.clickSaveButton();

		// Verify address
		actualValue = customerAddressObject.getCustomerName("Automation FC", "name");
		verifyTrue(actualValue.contains(newFirstName + " " + newLastName));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "email");
		verifyTrue(actualValue.contains(newEmail));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "phone");
		verifyTrue(actualValue.contains(phoneNumber));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "fax");
		verifyTrue(actualValue.contains(faxNumber));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "company");
		verifyTrue(actualValue.contains(newCompanyName));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "address1");
		verifyTrue(actualValue.contains(address1));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "address2");
		verifyTrue(actualValue.contains(address2));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "city-state-zip");
		verifyTrue(actualValue.contains(city + ", " + zipcode));

		actualValue = customerAddressObject.getCustomerName("Automation FC", "country");
		verifyTrue(actualValue.contains(country));
	}

	@Test
	public void TC_03_changePassword() {
		// navigate to change password
		customerAddressObject.navigateToPage_ContentList(driver, "/customer/changepassword");
		changePasswordObject = PageFactoryManager.getCustomerChangePasswordPageObject(driver);

		// update password
		changePasswordObject.inputPassword("OldPassword", GlobalConstants.PASSWORD);
		changePasswordObject.inputPassword("NewPassword", newPassword);
		GlobalConstants.PASSWORD = newPassword;
		changePasswordObject.inputPassword("ConfirmNewPassword", newPassword);

		changePasswordObject.clickChangePasswordButton();

		// logout
		startObject = changePasswordObject.logout(driver);

		// verify login failed with old password
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.login(email, password);
		verifyEquals(loginObject.getSummaryErrorMsg(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

		// verify login pass with new password
		startObject.navigateToPage_HeaderLink(driver, "login");
		loginObject = PageFactoryManager.getLoginPageObject(driver);
		loginObject.login(newEmail, newPassword);
		verifyTrue(loginObject.isMyAccountDisplayed());

	}

	@AfterClass
	public void afterClass() {
	}

}
