package pageUI;

public class CustomerAddressesPageUI {

	public static final String ADD_NEW_BUTTON = "//input[@value='Add new']";
	public static final String DYNAMIC_ADD_CUSTOMER_INPUT_FIELDS = "//input[@id='Address_%s']";
	public static final String DYNAMIC_ADD_CUSTOMER_SELECT_FIELDS = "//select[@id='Address_%s']";
	public static final String DYNAMIC_ADD_ITEM = "//strong[text()='%s']/parent::div/following-sibling::ul[@class='info']/li[@class='%s']";
	public static final String ADD_SAVE_BUTTON = "//input[@value='Save']";
}
