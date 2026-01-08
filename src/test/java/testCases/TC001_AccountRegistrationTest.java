package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass  {

	@Test(groups={"Regression","Master"})
	public void verify_Account_Registration() throws InterruptedException {
		try {
			logger.info("****TC001_AccountRegistrationTest started****");
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			logger.info("My Account clicked");
			hp.clickRegister();
			logger.info("Register clicked");
			AccountRegistrationPage regPage = new AccountRegistrationPage(driver);
			logger.info("Providing customer details");
			regPage.setFirstName(randomAlphabetic());
			regPage.setLastName(randomAlphabetic());
			regPage.setEmail(randomAlphabetic()+"@gmail.com");
			regPage.setTelephone(randomNumeric());
			String password = randomNumeric();
			regPage.setPassword(password);
			regPage.setConfirmPassword(password);
			regPage.setPrivacyPolicy();
			regPage.clickContinue();
			logger.info("Welcome message validation");
			String confMsg = regPage.getConfirmationMessage();
			if (confMsg.equals("Your Account Has Been Created!")) {
				Assert.assertTrue(true);
			}
			else {
				logger.debug("****Debug log enabled****");
				logger.error("Test got failed");
				Assert.assertTrue(false);
			}
		}
		catch(Exception e) {
			
			Assert.fail();
		}
		logger.info("****TC001_AccountRegistrationTest finished****");
	}
	
}
