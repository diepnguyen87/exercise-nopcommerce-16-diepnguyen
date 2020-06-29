package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import commons.AbstractPage;
import pageUI.AbstractPageUI;
import pageUI.CustomerInfosPageUI;

public class CustomerInfosPageObject extends AbstractPage {

	private WebDriver driver;
	private Select select;

	public CustomerInfosPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void updateDateOfBirth(String month, String day, String year) {
		select = new Select(findElement(driver, CustomerInfosPageUI.DYNAMIC_DOB_SELECT, "Day"));
		select.selectByVisibleText(day);

		select = new Select(findElement(driver, CustomerInfosPageUI.DYNAMIC_DOB_SELECT, "Month"));
		select.selectByVisibleText(month);

		select = new Select(findElement(driver, CustomerInfosPageUI.DYNAMIC_DOB_SELECT, "Year"));
		select.selectByVisibleText(year);

	}
	
}
