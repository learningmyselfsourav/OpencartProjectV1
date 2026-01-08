package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage{

	//Constructor
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
	//Locators
	@FindBy(name="firstname") WebElement txtFirstName;
	@FindBy(name="lastname") WebElement txtLastName;
	@FindBy(name="email") WebElement txtEmail;
	@FindBy(name="telephone") WebElement txtTelephone;
	@FindBy(name="password") WebElement txtPwd;
	@FindBy(name="confirm") WebElement txtCnfPwd;
	@FindBy(name="agree") WebElement chkboxAgree;
	@FindBy(xpath="//input[@type='submit']") WebElement btnContinue;
//	@FindBy(xpath="//h1[normalize-spcae()='Your Account Has Been Created!']") WebElement msgConfirmation;
	@FindBy(xpath="//div[@id='content']//h1") WebElement msgConfirmation;	//div[@id='content']//h1
	//Action methods
	public void setFirstName(String fName) {
		txtFirstName.sendKeys(fName);
	}
	public void setLastName(String lName) {
		txtLastName.sendKeys(lName);
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	public void setTelephone(String phone) {
		txtTelephone.sendKeys(phone);
	}
	public void setPassword(String pwd) {
		txtPwd.sendKeys(pwd);
	}
	public void setConfirmPassword(String pwd) {
		txtCnfPwd.sendKeys(pwd);
	}
	public void setPrivacyPolicy() {
		chkboxAgree.click();
	}
	public void clickContinue() {
		//solution 1:
		btnContinue.click();
		//solution 2:
//		btnContinue.submit();
//		//solution 3:
//		Actions act = new Actions(driver);
//		act.moveToElement(btnContinue).click().perform();
//		//Solution 4:
//		JavascriptExecutor js = new (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click()", btnContinue);
//		//Solution 5:
//		btnContinue.sendKeys(Keys.RETURN);
//		//Solution 6:
//		WebDriverWait myWait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		myWait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
	}
	public String getConfirmationMessage() {
		try {
			return(msgConfirmation.getText());
		}
		catch (Exception e) {
			return(e.getMessage());
		}
	}
	
}
