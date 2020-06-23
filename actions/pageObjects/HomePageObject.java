package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.HomePageUI;

public class HomePageObject extends AbstractPage {

	private WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickContinueButton() {
		waitForElementVisible(driver, HomePageUI.REGISTER_CONTINUE_BUTTON);
		clickToElement(driver, HomePageUI.REGISTER_CONTINUE_BUTTON);
	}

//	public MyAccountPageObject clickMyAccountLink() {
//		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
//		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
//		return PageFactoryManager.getMyAccountPageObject(driver);
//	}
	
}
