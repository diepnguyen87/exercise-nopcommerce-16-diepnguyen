package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.CustomerAddressesPageUI;

public class CustomerAddressesPageObject extends AbstractPage {

	private WebDriver driver;

	public CustomerAddressesPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputNewInfos(String fieldName, String value) {
		waitForElementVisible(driver, CustomerAddressesPageUI.ADD_CUSTOMER_INPUT_FIELDS, fieldName);
		sendKeyToElement(driver, CustomerAddressesPageUI.ADD_CUSTOMER_INPUT_FIELDS, value, fieldName);
	}

	public void clickAddNewButton() {
		waitForElementVisible(driver, CustomerAddressesPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, CustomerAddressesPageUI.ADD_NEW_BUTTON);
	}

	public void selectNewInfos(String fieldName, String value) {
		waitForElementVisible(driver, CustomerAddressesPageUI.ADD_CUSTOMER_SELECT_FIELDS, fieldName);
		selectItemInDropdown(driver, CustomerAddressesPageUI.ADD_CUSTOMER_SELECT_FIELDS , value, fieldName);
	}

	public void clickSaveButton() {
		waitForElementClickable(driver, CustomerAddressesPageUI.ADD_SAVE_BUTTON);
		clickToElement(driver, CustomerAddressesPageUI.ADD_SAVE_BUTTON);
	}

	public String getCustomerName(String addressTitle, String fieldName) {
		waitForElementVisible(driver, CustomerAddressesPageUI.ADD_ITEM, addressTitle, fieldName);
		return getElementText(driver, CustomerAddressesPageUI.ADD_ITEM, addressTitle, fieldName);
	}

}
