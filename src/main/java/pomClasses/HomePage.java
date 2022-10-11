package pomClasses;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilClasses.Util1;

public class HomePage extends Util1 {

static WebDriver driver;
	
	@FindBy (xpath="(//div[@class='_1psGvi _3BvnxG'])[1]")
	private WebElement myAccount;
		
	@FindBy (xpath="//div[text()='My Profile']")
	private WebElement myProfileText;
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement searchBox;
	
	@FindBy(xpath="//div[@class='_2kHMtA']")
	private WebElement productList;

	@FindBy(xpath="//div[@class='W_R1IA']")
	private WebElement textOnProductList;
	                         
	@FindBy(xpath="//div[@class='_30jeq3 _1_WHN1']")
	private List<WebElement> productPriceList;
	
	public HomePage(WebDriver driver){
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public String getProfileName() throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		WebElement loginText = wait.until(ExpectedConditions.visibilityOf(myAccount));
		
		String pName = loginText.getText();
		return pName;
	}

	public void SearchProduct() {
		searchBox.sendKeys("Realme");
		searchBox.sendKeys(Keys.ENTER);
	}
	
	public String getValidText() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		wait.until(ExpectedConditions.visibilityOf(productList));
		
		String actualText = textOnProductList.getText();
		return actualText;
	}
	
	public String getLowestPriceOfList() {
		List<Integer> priceList = new ArrayList<>();
		
		for(WebElement priceElement: productPriceList) {
			priceList.add(Integer.parseInt(priceElement.getText().replace("₹", "").replace(",", "")));	
		}
		System.out.println(priceList);
		Collections.sort(priceList);
		return priceList.get(0).toString();
	}
	
	public void switchToPage(int a) {
		driver.findElement(By.xpath("//a[@class='ge-49M' and text()='"+a+"']")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='ge-49M _2Kfbh8' and text()='" +a+ "']")));	
	}
	
	public void hoverOnProfile() {
		
		Actions act = new Actions(driver);
		act.moveToElement(myAccount).perform();
	}
	
	public void clickOnMyProfile() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", myProfileText);
		
	}
	
	
}
