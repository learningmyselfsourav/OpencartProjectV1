package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;		//log4j
import org.apache.logging.log4j.Logger;			//log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String browser) throws IOException {
		
		FileReader file = new FileReader("./src//test//resources//config.properties"); //Loading Config.Properties file:
		p=new Properties();		//new properties file object P
		p.load(file);
		logger = LogManager.getLogger(this.getClass()); //this will start logging
		//DesiredCapabilities capabilities = new DesiredCapabilities();
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("linux")) {
				capabilities.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("Browser name is NOT matched");
				return;
			}
			switch(browser.toLowerCase()) {
				case "chrome": capabilities.setBrowserName("chrome"); break;
				case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
				case "firefox": capabilities.setBrowserName("firefox"); break;
				default: System.out.println("Invalid browser"); return;
			}
		driver = new RemoteWebDriver(new URL("http://192.168.29.111:4444/wd/hub"),capabilities);
		logger.info("Test is running in Remote session");
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(browser.toLowerCase()) {
				case "chrome": driver = new ChromeDriver(); break;
				case "edge": driver = new EdgeDriver(); break;
				case "firefox": driver = new FirefoxDriver(); break;
				default: System.out.println("Invliad browser name"); return; //return means it will exit from all
		}
			logger.info("Test is running in Local session");	
	}
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));	//reading URL from properties file
		driver.manage().window().maximize();
}
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void teardown() {
		driver.quit();
	}
	
	//User-defined methods:
	public String randomAlphabetic() {
		String randomStrings = RandomStringUtils.randomAlphabetic(6);
		return randomStrings;
	}
	
	public String randomNumeric() {
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return randomNumber;
	}
	
	public String randomAlphaNumeric() {
		String randomStrings = RandomStringUtils.randomAlphabetic(6);
		String randomNumber = RandomStringUtils.randomNumeric(10);
		return (randomStrings+"!@"+randomNumber);
	}
	
	public String captureScreen(String tname) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
