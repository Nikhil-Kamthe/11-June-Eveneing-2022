package pomClasses;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilClasses.Util1;

public class ProfilePage extends Util1 {

static WebDriver driver;
	
	@FindBy(xpath = "//div[text()='Apurva Kamthe']")
	private WebElement profileName;
	
	@FindBy(xpath = "//div[text()='Manage Addresses']")
	private WebElement manageAddress;
	
	@FindBy(xpath = "//div[@class='_1QhEVk']")
	private WebElement addAddress;
	
	@FindBy(xpath = "//textarea[@tabindex='5']")
	private WebElement fullAddress;
	
	@FindBy(xpath = "//button[text()='Save']")
	private WebElement saveButton;
	

	
	public ProfilePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
		this.driver = driver;
	}
	
	public String checkUserIsOnProfilePage() {
		String pName = profileName.getText();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(profileName));
		return pName;
	}
	
	public void clickOnManageAddress() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(manageAddress));
		manageAddress.click();
	}
	
	
	public void addAddress(List<String> addressDetail) throws InterruptedException {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(addAddress));
		addAddress.click();
		
		for (int i=1; i<=4; i++)		
		{
			driver.findElement(By.xpath("//input[@tabindex='" +i+ "']")).sendKeys(addressDetail.get(i-1));
		}
		fullAddress.sendKeys(addressDetail.get(4));
		saveButton.click();

		
	}
	
	
}
