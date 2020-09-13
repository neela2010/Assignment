package homeWork;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyBoardEvent {
	
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
	public void logIn() {
		

		WebElement USERNAME_FIELD = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_FIELD = driver.findElement(By.xpath("//input[@id='password']"));
		
		USERNAME_FIELD.sendKeys("demo@techfios.com");
		PASSWORD_FIELD.sendKeys("abc123");
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).build().perform();
		
		
	}
	

}  
