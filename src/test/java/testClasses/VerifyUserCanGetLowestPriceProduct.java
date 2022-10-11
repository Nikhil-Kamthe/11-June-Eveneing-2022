package testClasses;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import baseClasses.Base1;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class VerifyUserCanGetLowestPriceProduct {

	
	static WebDriver driver;
	LoginPage lp;
	HomePage hp;
	
	
	@BeforeClass
	@Parameters("browser")
	public void beforeClass(String browser) throws IOException {
		driver = Base1.getDriver(browser);
		
	}
	
	@BeforeMethod
	public void beforeMethod(){
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		
	}
		
	@Test(priority = 1)
	public void verifyUserAbleToSearchProduct() throws InterruptedException{
		hp.SearchProduct();
		String actualText= hp.getValidText();
		System.out.println(actualText);
		if(actualText.contains("Showing 1 –")) {
			System.out.println("User is Successfully searched the product");
		}else {
			System.out.println("User is not Successfully searched the product");
		}
	}
	
	@Test(priority = 2)
	public void getLowestPriceofProductFromEachPage() {
		
		Map<Integer, String> lowPriceMap = new HashMap<>();
		
		for(int i=1; i<=5; i++) {
			
			if(i != 1) {
				
				hp.switchToPage(i);
			}
			hp = new HomePage(driver);
			
		//	lowsetPriceFron5Page.add(hp.getLowestPriceOfProduct());
			
			lowPriceMap.put(i, hp.getLowestPriceOfList());
		}
		
		System.out.println(lowPriceMap);	
		
	}

	@AfterMethod
	public void afterMethod() {
	
	}
	
	@AfterClass
	public void afterClass() {
	
	}
	
	
	
	
}
