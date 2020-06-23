package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import commons.AbstractPage;
import pageUI.CustomerInfosPageUI;

public class CustomerInfosPageObject extends AbstractPage {

	private WebDriver driver;
	private Select select;

	public CustomerInfosPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void updateGender(String gender) {
		String genderXpath = castToObject(CustomerInfosPageUI.GENDER_RADIO_BUTTON, gender);
		waitForElementVisible(driver, genderXpath);
		if (!isElementSelected(driver, genderXpath)) {
			clickToElement(driver, genderXpath);
		}
	}

	public void updateFirstName(String firstName) {
		waitForElementVisible(driver, CustomerInfosPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, CustomerInfosPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void updateLastName(String lastName) {
		waitForElementVisible(driver, CustomerInfosPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, CustomerInfosPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void updateDateOfBirth(String month, String day, String year) {
		select = new Select(findElement(driver, CustomerInfosPageUI.DOB_SELECT, "Day"));
		select.selectByVisibleText(day);

		select = new Select(findElement(driver, CustomerInfosPageUI.DOB_SELECT, "Month"));
		select.selectByVisibleText(month);

		select = new Select(findElement(driver, CustomerInfosPageUI.DOB_SELECT, "Year"));
		select.selectByVisibleText(year);

	}

	public void updateEmail(String email) {
		waitForElementVisible(driver, CustomerInfosPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, CustomerInfosPageUI.EMAIL_TEXTBOX, email);
	}

	public void updateCompanyName(String companyName) {
		waitForElementVisible(driver, CustomerInfosPageUI.COMPANY_TEXTBOX);
		sendKeyToElement(driver, CustomerInfosPageUI.COMPANY_TEXTBOX, companyName);
	}

	public CustomerInfosPageObject clickSaveButton() {
		waitForElementVisible(driver, CustomerInfosPageUI.SAVE_BUTTON);
		clickToElement(driver, CustomerInfosPageUI.SAVE_BUTTON);
		return PageFactoryManager.getCustomerInfosPageObject(driver);
	}

	public String getValueOfField(String fieldName) {
		String value = "";
		switch (fieldName) {
		case "female":
			waitForElementVisible(driver, CustomerInfosPageUI.GENDER_RADIO_BUTTON, "female");
//			System.out.println(isElementSelected(driver, MyAccountPageUI.GENDER_RADIO_BUTTON, "female"));
			if (isElementSelected(driver, CustomerInfosPageUI.GENDER_RADIO_BUTTON, "female")) {
				value = "female";
			}
			break;
		case "first name":
			waitForElementVisible(driver, CustomerInfosPageUI.FIRST_NAME_TEXTBOX);
			value = getAttributeValue(driver, CustomerInfosPageUI.FIRST_NAME_TEXTBOX, "value");
			break;
		case "last name":
			waitForElementVisible(driver, CustomerInfosPageUI.LAST_NAME_TEXTBOX);
			value = getAttributeValue(driver, CustomerInfosPageUI.LAST_NAME_TEXTBOX, "value");
			break;
		case "birth day":
			waitForElementVisible(driver, CustomerInfosPageUI.DOB_SELECT, "Day");
			value = getSelectedItemInDropdown(driver, CustomerInfosPageUI.DOB_SELECT, "Day").getText();
			break;
		case "birth month":
			waitForElementVisible(driver, CustomerInfosPageUI.DOB_SELECT, "Month");
			value = getSelectedItemInDropdown(driver, CustomerInfosPageUI.DOB_SELECT, "Month").getText();
			break;
		case "birth year":
			waitForElementVisible(driver, CustomerInfosPageUI.DOB_SELECT, "Year");
			value = getSelectedItemInDropdown(driver, CustomerInfosPageUI.DOB_SELECT, "Year").getText();
			break;
		case "email":
			waitForElementVisible(driver, CustomerInfosPageUI.EMAIL_TEXTBOX);
			value = getAttributeValue(driver, CustomerInfosPageUI.EMAIL_TEXTBOX, "value");
			break;
		case "company":
			waitForElementVisible(driver, CustomerInfosPageUI.COMPANY_TEXTBOX);
			value = getAttributeValue(driver, CustomerInfosPageUI.COMPANY_TEXTBOX, "value");
			break;
		}
		return value;
	}

}
