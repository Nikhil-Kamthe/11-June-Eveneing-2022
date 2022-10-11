package testClasses;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.Base1;
import pomClasses.HomePage;
import pomClasses.LoginPage;
import pomClasses.ProfilePage;

public class VerifyUserCanAddNewAddresses {

static WebDriver driver;
	
	LoginPage lp;
	HomePage hp;
	ProfilePage pp;
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		
		driver = Base1.getDriver(browser);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		pp = new ProfilePage(driver);
	}
	
	@Test(priority = 1)
	public void verifyUserCanOpenProfilePage() {
		hp.hoverOnProfile();
		hp.clickOnMyProfile();
	}
	
	@Test(priority = 2)
	public void verifyUserIsOnProfilPage() {
		String pName = pp.checkUserIsOnProfilePage();
		
		if(pName.contains("Apurva"))
		{
			System.out.println("User is on Profile Page");
		} else {
			System.out.println("User is not on Profile Page");
		}
	}
	
	@Test(priority = 3)
	public void addAddress() {
		pp.clickOnManageAddress();
		
	}
	
	
	@DataProvider(name = "testData")
	public Object[][] getData() {
		Object[][] adr = {{"Nikhil","8805321258","411043","Pune","Sambhaji Nagar, Dhankawadi"}, {"Devendra","8983063190","411046","Pune","Bharati Vidyapith"}, {"Akash","9923420686","411043","Pune","Samhaji Nagar, Dhankawadi"}, {"Sourabh","9881299707","411046","Pune","Samhaji Nagar, Dhankawadi"}};
		return adr;
	}
	
	@Test(priority = 3, dataProvider = "testData")
	public void verifyUserAbleToAddAddress(String name, String mobile, String pincode, String locality, String fullAddress) throws InterruptedException {
		
		List<String> addressDetail = Arrays.asList(name, mobile, pincode, locality, fullAddress); 
		pp.addAddress(addressDetail);
		
	}
	
	@AfterMethod
	public void afterMethod() {
	
	}
	
	@AfterClass
	public void afterClass() {
		
		Base1.unloadingDriver();
	
	}
	
	
	
}
