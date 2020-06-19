package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.RegisterPageUI;

public class RegisterPageObject extends AbstractPage {

	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickRegisterButton() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public boolean isErrorMessageDisplayed(String xpathLocator, String expectedErrorMsg) {
		waitForElementVisible(driver, xpathLocator);
		String actualErrorMsg = getElementText(driver, xpathLocator);
		if(!actualErrorMsg.equals(expectedErrorMsg)) {
			return false;
		}
		return true;
	}
}
