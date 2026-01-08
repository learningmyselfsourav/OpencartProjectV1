package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	//Constructor - derived from BasePage
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(xpath="//nav[@id='top']//li[@class='dropdown']/a") WebElement linkMyAccount;
	@FindBy(xpath="//a[text()='Register']") WebElement linkRegister;
	@FindBy(xpath="//a[text()='Login']") WebElement linkLogin;
	
	//Action methods
	public void clickMyAccount() {
		linkMyAccount.click();
	}
	public void clickRegister() {
		linkRegister.click();
	}
	public void clickLogin() {
		linkLogin.click();
	}
}
