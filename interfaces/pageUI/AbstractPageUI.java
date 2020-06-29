package pageUI;

public class AbstractPageUI {

	public static final String DYNAMIC_HEADER_LINK = "//a[@class='ico-%s']";
	public static final String DYNAMIC_FOOTER_LINK = "//a[text()='%s']";
	public static final String DYNAMIC_HEADER_MENU = "//ul[@class='top-menu notmobile']//a[contains(text(),'%s')]";
	public static final String DYNAMIC_HEADER_SUB_MENU = "//ul[@class='top-menu notmobile']//a[contains(text(), '%s')]/following-sibling::ul[@class='sublist first-level']//a[contains(text(),'%s')]";

	//textbox
	public static final String DYNAMIC_TEXTBOX = "//input[@name='%s']";
	
	//textarea
	public static final String DYNAMIC_TEXTAREA = "//textarea[@name='%s']";
	
	//radiobutton
	public static final String DYNAMIC_RADIO_BUTTON = "//input[@type='radio' and @value='%s']";
	
	//checkbutton
	public static final String DYNAMIC_CHECK_BUTTON = "//input[@type='checkbox' and name='%s']";
	
	//Menu link
	public static final String DYNAMIC_LINK = "//a[text()='%s']";
	
	//Button
	public static final String DYNAMIC_BUTTON = "//input[@value='%s']";
	
	//Message
	public static final String DYNAMIC_MESSAGE = "//p[@class='heading3' and text()='%s']";
	
	//
	public static final String DYNAMIC_VALUE_BY_COLUMN_NAME = "//td[contains(text(),'%s')]/following-sibling::td";

}

