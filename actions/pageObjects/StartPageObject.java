package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.StartPageUI;

public class StartPageObject extends AbstractPage {

	private WebDriver driver;
	
	public StartPageObject(WebDriver driver) {
		this.driver = driver;
	}


	

//	public RegisterPageObject clickRegisterLink() {
//		waitForElementVisible(driver, StartPageUI.REGISTER_LINK);
//		clickToElement(driver, StartPageUI.REGISTER_LINK);
//		return new RegisterPageObject(driver);
//	}

//	public LoginPageObject clickLoginLink() {
//		waitForElementVisible(driver, StartPageUI.LOGIN_LINK);
//		clickToElement(driver, StartPageUI.LOGIN_LINK);
//		return new LoginPageObject(driver);
//	}
}
