package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.CustomerChangePasswordPageUI;

public class CustomerChangePasswordPageObject extends AbstractPage {

	private WebDriver driver;

	public CustomerChangePasswordPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputPassword(String fieldName, String inputValue) {
		waitForElementVisible(driver, CustomerChangePasswordPageUI.DYNAMIC_PASSWORD_TEXTBOX, fieldName);
		sendKeyToElement(driver, CustomerChangePasswordPageUI.DYNAMIC_PASSWORD_TEXTBOX, inputValue, fieldName);
	}

	public void clickChangePasswordButton() {
		waitForElementClickable(driver, CustomerChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
		clickToElement(driver, CustomerChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
	}
}
