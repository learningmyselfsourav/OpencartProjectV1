package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	//Constructor - derived from BasePage
		public MyAccountPage(WebDriver driver) {
			super(driver);
		}
	
	//Locators
	@FindBy(xpath="//div//h2[text()='My Account']") WebElement msgHeading;
	@FindBy(xpath="//div[@class='list-group']//a[text()='Logout']") WebElement linkLogout;
	
	//Action methods
	public boolean isMyAccountExists() {
		try {
			return (msgHeading.isDisplayed());
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		linkLogout.click();
	}
}
