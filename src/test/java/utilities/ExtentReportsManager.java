	package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportsManager implements ITestListener {
	
	public ExtentSparkReporter sparkReporter;	//UI of the report
	public ExtentReports extent;				//populate common info in the report
	public ExtentTest test;		//create test case entry in the report and update status
	String reportName;
	public void onStart(ITestContext testContext) {
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentDateTimeStamp =df.format(dt);
		*/
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());	//current time stamp
		reportName = "TestReport-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName); //specify the report path
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");	//Title of the report
		sparkReporter.config().setReportName("OpencartFunctional Testing");		//Name of the report
		sparkReporter.config().setTheme(Theme.DARK);						//theme of the report
		//attaching the extent with sparkReporter
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application Name", "Opencart");	//key-vaue pair
		extent.setSystemInfo("Module", "Admin");		//key-vaue pair
		extent.setSystemInfo("Sub-module", "Customers");		//key-vaue pair
		extent.setSystemInfo("User name", System.getProperty("user.name"));		//key-vaue pair
		extent.setSystemInfo("Environment", "QA");
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating system", os);
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
			extent.setSystemInfo("Groups", includedGroups.toString());
	  }
	
	public void onTestSuccess(ITestResult result) {		//test method name will be assigned in result parameter
		 test = extent.createTest(result.getTestClass().getName());	//create a new entry in the report along with test method name
		 test.assignCategory(result.getMethod().getGroups());	//to display groups in the reports
		 test.log(Status.PASS,result.getName()+" got successfully executed");	//update the log as Passed <testcase name>
	  }
	
	public void onTestFailure(ITestResult result) {		//test method name will be assigned in result parameter
		test = extent.createTest(result.getTestClass().getName());	//create a new entry in the report along with test method name
		test.assignCategory(result.getMethod().getGroups());	//to display groups in the reports
		test.log(Status.FAIL, result.getName()+" got Failed");	//update the log as Failed <testcase name>
		test.log(Status.FAIL, result.getThrowable().getMessage()); //print the Console Error in report
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());	//capture screenshot
			test.addScreenCaptureFromPath(imgPath);		//attach to report
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	  }
	
	public void onTestSkipped(ITestResult result) {		//test method name will be assigned in result parameter
		test = extent.createTest(result.getClass().getName());	//create a new entry in the report along with test method name
		test.assignCategory(result.getMethod().getGroups());	//to display groups in the reports
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());	
		
	  }
	
	public void onFinish(ITestContext context) {
		extent.flush(); 		//mandatory method for report
		String pathOfExtentReport = System.getProperty("user.dir")+".\\reports\\"+reportName;
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		/*
		//to send the report to the email address:
		try {
			URL url = new URL("file:///"+System.getProperty("user.dir")+".\\reports\\"+reportName); //converting string into URL format
			//create the email message
			ImageHtmlEmail email = new ImageHtmlEmail();
			email.setDataSourceResolver(new DataSourceUrlResolver(url));
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator("username@gmail.com","password"));
			email.setSSLOnConnect(true);
			email.setFrom("sender_email@gmail.com");	//sender email id
			email.setSubject("Automation Test Execution report");	
			email.setMsg("Please find the attached copy of the automation test report");
			email.addTo("receiver_email@gmail.com");	//receiver email id
			email.attach(url, "extent report", "please check report");
			email.send();	//send the email
		}
		catch(Exception e2) {
			e2.printStackTrace();
		}
		*/
	  }
}
