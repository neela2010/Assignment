package homeWork;


/*Test Scenario: CRM -> Customers -> Add Contact - Add contact and validate contact added in List Contacts
Open Browser and go to site http://techfios.com/test/billing/?ng=admin/
Enter username:  demo@techfios.com 
Enter password: abc123
Click login button
Click on Customers button in the Side Navigation
Click on Add Customer
Fill in the Add Customer Form
Click submit
Go to CRM -> List Customer 
*/
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Session4 {

	WebDriver driver;

	@Before
	public void launchBrowser() {

		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://www.techfios.com/ibilling/?ng=admin/");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void logIn() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);

		WebElement USERNAME_FIELD = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_BUTTON = driver.findElement(By.xpath("//button[@name='login']"));

		USERNAME_FIELD.sendKeys("demo@techfios.com");
		PASSWORD_FIELD.sendKeys("abc123");
		SIGNIN_BUTTON.click();

		WebElement CUSTOMERS_NAV = driver.findElement(By.xpath("//i[@class='icon-users']"));

		Actions action = new Actions(driver);
		action.moveToElement(CUSTOMERS_NAV).build().perform();

		WebElement ADD_CUSTOMER_LINK = driver.findElement(By.xpath("//a[contains(text(),'Add Customer')]"));
		Thread.sleep(10000);
		ADD_CUSTOMER_LINK.click();

		WebElement FULL_NAME_FIELD = driver.findElement(By.xpath("//input[@id='account' and @class='form-control']"));
		wait.until(ExpectedConditions.visibilityOf(FULL_NAME_FIELD));
		FULL_NAME_FIELD.sendKeys("NeelaAfroze");

		WebElement COMPANY_DROPDOWN = driver.findElement(By.xpath("//select[@id='cid']"));

		Select sel = new Select(COMPANY_DROPDOWN);

		sel.selectByVisibleText("Techfios");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("demo3@techfios.com");
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys("6425080011");
		driver.findElement(By.xpath("//input[@name='address']")).sendKeys("3809 LandsDowneDr");
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys("McKinney");
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys("Texas");
		driver.findElement(By.xpath("//input[@name='zip']")).sendKeys("75072");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@id='submit']")).click();

		Thread.sleep(5000);
		WebElement CUSTOMERS_NAV2 = driver.findElement(By.xpath("/html/body/section/div/nav/div/ul/li[3]/a/i"));
		Thread.sleep(5000);
		action.moveToElement(CUSTOMERS_NAV2).build().perform();

		WebElement LIST_CUSTOMER_LINK = driver.findElement(By.xpath("//a[contains(text(),'List Customers')]"));
		Thread.sleep(5000);
		LIST_CUSTOMER_LINK.click();

		WebElement SEARCH_FIELD = driver.findElement(By.xpath("//input[@id='foo_filter' and @name='name']"));
		SEARCH_FIELD.sendKeys("NeelaAfroze");

		WebElement SEARCH_RESULT = driver.findElement(By.xpath("//a[contains(text(),'NeelaAfroze')]"));
		Thread.sleep(2000);
		String Actual = SEARCH_RESULT.getText();
		System.out.println(Actual);

		Assert.assertEquals("NeelaAfroze", Actual);

	}

	@After
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		driver.quit();

	}

}
