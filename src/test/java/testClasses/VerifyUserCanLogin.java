package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.Base1;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class VerifyUserCanLogin {

	static WebDriver driver;
	
	ExtentHtmlReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	
	LoginPage lp;
	HomePage hp;
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		
		htmlReporter = new ExtentHtmlReporter("ExtentReports\\reports.html");
		
		reports = new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		test = reports.createTest("VerifyUserCanLogin");
		
		driver = Base1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod(){
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
	}
		
	@Test
	public void test1() throws InterruptedException, IOException{
		
		lp.enterMailID();
		lp.entePassword();
		lp.enterSubmit();

		String profileName = hp.getProfileName();
		
		if(profileName.contains("Apurva")) {
			System.out.println("User is logged in");
		}else {
			System.out.println("User is not logged in");
		}
	}
	
	@AfterMethod
	public void afterMethod() {
	
	}
	
	@AfterClass
	public void afterClass() {
		
		reports.flush();
		
	}
	
	
	
}
