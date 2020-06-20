package pageObjects;

import org.openqa.selenium.Keys;
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
		System.out.println("Actual: " + actualErrorMsg);
		System.out.println("Actual: " + expectedErrorMsg);
		
		
		if(!actualErrorMsg.equals(expectedErrorMsg)) {
			return false;
		}
		return true;
	}

	public void inputFirstName(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstName);
	}

	public void inputLastName(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastName);
	}

	public void inputEmail(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);	
	}

	public void inputPasword(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputConfirmPassword(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmPassword);
	}

	public void pressControlTab() {
		sendKeyboardToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, Keys.TAB);
	}

	public String createRandomEmail(String firstName, String lastName) {
		return firstName+"."+lastName+getRandomNumber()+"@gmail.com";
	}
}
