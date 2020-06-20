package pageUI;

public class LoginPageUI {

	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String LOGIN_BUTTON = "//input[contains(@class, 'login-button')]";
	
//	public static final String EMAIL_ERROR_MESSAGE = "//span[@id='Email-error']";
	public static final String EMAIL_ERROR_MESSAGE = "//span[@class='field-validation-error']";
	public static final String PASSWORD_ERROR_MESSAGE = "";
	
	public static final String SUMMARY_ERROR_MESSAGE = "//div[contains(@class, 'message-error')]";
}
