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
		waitForElementVisible(driver, CustomerAddressesPageUI.DYNAMIC_ADD_CUSTOMER_INPUT_FIELDS, fieldName);
		sendKeyToElement(driver, CustomerAddressesPageUI.DYNAMIC_ADD_CUSTOMER_INPUT_FIELDS, value, fieldName);
	}

	public void selectNewInfos(String fieldName, String value) {
		waitForElementVisible(driver, CustomerAddressesPageUI.DYNAMIC_ADD_CUSTOMER_SELECT_FIELDS, fieldName);
		selectItemInDropdown(driver, CustomerAddressesPageUI.DYNAMIC_ADD_CUSTOMER_SELECT_FIELDS , value, fieldName);
	}

	public String getCustomerInfo(String addressTitle, String fieldName) {
		waitForElementVisible(driver, CustomerAddressesPageUI.DYNAMIC_ADD_ITEM, addressTitle, fieldName);
		return getElementText(driver, CustomerAddressesPageUI.DYNAMIC_ADD_ITEM, addressTitle, fieldName);
	}

}
