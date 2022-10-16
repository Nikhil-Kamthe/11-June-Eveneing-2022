package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.Base1;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class VerifyUserCanLogin {

	static WebDriver driver;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest extentTest;
	
	
	LoginPage lp;
	HomePage hp;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		
		driver = Base1.getDriver(browser);
		
		htmlReporter = new ExtentHtmlReporter("ExtentReports\\reports.html");
		
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		extentTest = reports.createTest("VerifyUserCanLogin");
		
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
		
	@Test
	public void VerifyUserCanLogIn() throws InterruptedException, IOException{
		
		lp.enterMailID();
		lp.entePassword();
		lp.enterSubmit();

		String profileName = hp.getProfileName();
		
		Assert.assertEquals(profileName, "Apurva", "Profile name is not matching");
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(Status.PASS, "Test :" + result.getName());	
		}else if(result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(Status.FAIL, "Test :" + result.getName());
		}else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(Status.SKIP, "Test :" + result.getName());
		}
	}
	
	@AfterClass
	public void afterClass() {
		reports.flush();
	}
	
	
	
}
