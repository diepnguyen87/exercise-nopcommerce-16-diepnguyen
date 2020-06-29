package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.AbstractPageUI;
import pageUI.LoginPageUI;

public class LoginPageObject extends AbstractPage {

	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getEmailErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public String getSummaryErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.SUMMARY_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.SUMMARY_ERROR_MESSAGE);
	}

	public boolean isMyAccountDisplayed() {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_HEADER_LINK, "account");
		return isElementDisplayed(driver, AbstractPageUI.DYNAMIC_HEADER_LINK, "account");
	}
	
	public HomePageObject login(String email, String password) {
		inputToDynamicTextbox(driver, "Email", email);
		inputToDynamicTextbox(driver, "Password", password);
		clickToDynamicButton(driver, "Log in");
		return PageFactoryManager.getHomePageObject(driver);
	}

}
