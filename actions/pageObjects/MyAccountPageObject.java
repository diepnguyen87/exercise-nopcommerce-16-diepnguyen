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
	
	
}
