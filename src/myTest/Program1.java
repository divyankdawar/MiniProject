package myTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;


public class Program1 {
	
	WebDriver driver = new ChromeDriver();
	@Test
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","\"E:\\Canada Preparation\\Chrome & Gecko Driver\\chromedriver_win32\\chromedriver.exe");
		String baseUrl = "https://rahulshettyacademy.com/AutomationPractice/";
		
		driver.get(baseUrl);
		driver.manage().window().maximize();
		
		//Radio Button Example
		driver.findElement(By.cssSelector("input[value='radio1']")).click();
		
		//Suggestion Class Example
		WebElement suggestionInput = driver.findElement(By.id("autocomplete"));
		
		suggestionInput.sendKeys("United");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ui-menu-item")));
		
		List<WebElement> suggestionList = driver.findElements(By.cssSelector(".ui-menu-item"));
		System.out.println(suggestionList);
		for(WebElement suggestion : suggestionList) {
			if(suggestion.getText().equals("United States (USA)")) {
				suggestion.click();
				break;
				
			}
			
		}
	
		
		
	}
	
	

}
