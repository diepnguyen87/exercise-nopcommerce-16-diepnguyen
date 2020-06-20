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

	public void selectGender(String gender) {
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

	public void selectDateOfBirth(String month, String day, String year) {
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
}
