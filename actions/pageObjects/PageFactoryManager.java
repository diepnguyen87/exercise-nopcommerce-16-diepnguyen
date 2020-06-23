package pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryManager {

	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static LoginPageObject getLoginPageObject(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static StartPageObject getStartPageObject(WebDriver driver) {
		return new StartPageObject(driver);
	}

	public static MyAccountPageObject getMyAccountPageObject(WebDriver driver) {
		return new MyAccountPageObject(driver);
	}
	
	public static CustomerInfosPageObject getCustomerInfosPageObject(WebDriver driver) {
		return new CustomerInfosPageObject(driver);
	}

	public static CustomerAddressesPageObject getCustomerAddressesPageObject(WebDriver driver) {
		return new CustomerAddressesPageObject(driver);
	}

}
