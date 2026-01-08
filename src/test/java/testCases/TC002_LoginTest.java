package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{

	@Test(groups={"Sanity","Master"})
	public void verify_login() {
		logger.info("**** Starting of Login Test ****");
		try {
			//HomePage
			HomePage hp = new HomePage(driver);
			hp.clickMyAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp = new LoginPage(driver);
			lp.setEmailID(p.getProperty("email"));
			lp.setPassword(p.getProperty("password"));
			lp.clickLogin();
			
			//MyAccountPage
			MyAccountPage myAcc = new MyAccountPage(driver);
			boolean targetPage = myAcc.isMyAccountExists();
			
			//Validation
			//Assert.assertEquals(targetPage, true, "Login is Failed");	//OR below
			Assert.assertTrue(targetPage);
		}
		catch(Exception e) {
			Assert.fail();
		}
		logger.info("**** Finished of Login Test ****");	
	}
}
