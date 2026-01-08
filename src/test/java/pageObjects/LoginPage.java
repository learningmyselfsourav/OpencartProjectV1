package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	//Constructor - derived from BasePage
	public LoginPage(WebDriver driver) {
		super(driver);
	}
		
	//Locators
	@FindBy(name="email") WebElement txtEmail;
	@FindBy(name="password") WebElement txtPassword;
	@FindBy(xpath="//input[@type='submit']") WebElement btnLogin;
	
	//Action methods
	public void setEmailID(String email) {
		txtEmail.sendKeys(email);
	}
	public void setPassword(String password) {
		txtPassword.sendKeys(password);
	}
	public void clickLogin() {
		btnLogin.click();
	}
}
