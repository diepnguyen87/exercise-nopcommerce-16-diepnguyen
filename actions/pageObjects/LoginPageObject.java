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

	public void clickLoginButton() {
		waitForElementVisible(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getEmailErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputEmail(String email) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
	}

	public String getSummaryErrorMsg() {
		waitForElementVisible(driver, LoginPageUI.SUMMARY_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.SUMMARY_ERROR_MESSAGE);
	}

	public void inputPassword(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public boolean isMyAccountDisplayed() {
		waitForElementVisible(driver, AbstractPageUI.HEADER_LINK, "account");
		return isElementDisplayed(driver, AbstractPageUI.HEADER_LINK, "account");
	}
	
	public HomePageObject login(String email, String password) {
		inputEmail(email);
		inputPassword(password);
		clickLoginButton();
		return PageFactoryManager.getHomePageObject(driver);
	}

}
