package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import commons.AbstractPage;
import pageUI.MyAccountPageUI;

public class MyAccountPageObject extends AbstractPage {

	private WebDriver driver;
	private Select select;
	
	public MyAccountPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void updateGender(String gender) {
		String genderXpath = castToObject(MyAccountPageUI.GENDER_RADIO_BUTTON, gender);
		waitForElementVisible(driver, genderXpath);
		if(!isElementSelected(driver, genderXpath)) {
			clickToElement(driver, genderXpath);
		}
	}

	public void updateFirstName(String firstName) {
		waitForElementVisible(driver, MyAccountPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, MyAccountPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void updateLastName(String lastName) {
		waitForElementVisible(driver, MyAccountPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, MyAccountPageUI.LAST_NAME_TEXTBOX, lastName);	
	}

	public void updateDateOfBirth(String month, String day, String year) {
		select = new Select(findElement(driver, MyAccountPageUI.DOB_SELECT, "Day"));
		select.selectByVisibleText(day);
		
		select = new Select(findElement(driver, MyAccountPageUI.DOB_SELECT, "Month"));
		select.selectByVisibleText(month);
		
		select = new Select(findElement(driver, MyAccountPageUI.DOB_SELECT, "Year"));
		select.selectByVisibleText(year);
		
	}

	public void updateEmail(String email) {
		waitForElementVisible(driver, MyAccountPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, MyAccountPageUI.EMAIL_TEXTBOX, email);
	}

	public void updateCompanyName(String companyName) {
		waitForElementVisible(driver, MyAccountPageUI.COMPANY_TEXTBOX);
		sendKeyToElement(driver, MyAccountPageUI.COMPANY_TEXTBOX, companyName);	
	}

	public MyAccountPageObject clickSaveButton() {
		waitForElementVisible(driver, MyAccountPageUI.SAVE_BUTTON);
		clickToElement(driver, MyAccountPageUI.SAVE_BUTTON);
		return PageFactoryManager.getMyAccountPageObject(driver);
	}

	public String getValueOfField(String fieldName) {
		String value="";
		switch (fieldName) {
		case "female":
			waitForElementVisible(driver, MyAccountPageUI.GENDER_RADIO_BUTTON, "female");
//			System.out.println(isElementSelected(driver, MyAccountPageUI.GENDER_RADIO_BUTTON, "female"));
			if(isElementSelected(driver, MyAccountPageUI.GENDER_RADIO_BUTTON, "female")) {
				value = "female";
			}
			break;
		case "first name":
			waitForElementVisible(driver, MyAccountPageUI.FIRST_NAME_TEXTBOX);
			value = getAttributeValue(driver, MyAccountPageUI.FIRST_NAME_TEXTBOX, "value");
			break;
		case "last name":
			waitForElementVisible(driver, MyAccountPageUI.LAST_NAME_TEXTBOX);
			value = getAttributeValue(driver, MyAccountPageUI.LAST_NAME_TEXTBOX, "value");
			break;
		case "birth day":
			waitForElementVisible(driver, MyAccountPageUI.DOB_SELECT, "Day");
			value = getSelectedItemInDropdown(driver, MyAccountPageUI.DOB_SELECT, "Day").getText();
			break;
		case "birth month":
			waitForElementVisible(driver, MyAccountPageUI.DOB_SELECT, "Month");
			value = getSelectedItemInDropdown(driver, MyAccountPageUI.DOB_SELECT, "Month").getText();
			break;
		case "birth year":
			waitForElementVisible(driver, MyAccountPageUI.DOB_SELECT, "Year");
			value = getSelectedItemInDropdown(driver, MyAccountPageUI.DOB_SELECT, "Year").getText();
			break;
		case "email":
			waitForElementVisible(driver, MyAccountPageUI.EMAIL_TEXTBOX);
			value = getAttributeValue(driver, MyAccountPageUI.EMAIL_TEXTBOX, "value");
			break;
		case "company":
			waitForElementVisible(driver, MyAccountPageUI.COMPANY_TEXTBOX);
			value = getAttributeValue(driver, MyAccountPageUI.COMPANY_TEXTBOX, "value");
			break;
		}
		return value;
	}

}
