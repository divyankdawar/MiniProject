package myTest;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Program1 {

	WebDriver driver = new ChromeDriver();
	@BeforeMethod
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","\"E:\\Canada Preparation\\Chrome & Gecko Driver\\chromedriver_win32\\chromedriver.exe");
		String baseUrl = "https://rahulshettyacademy.com/AutomationPractice/";

		driver.get(baseUrl);
		driver.manage().window().maximize();

	}
	
	@Test
	public void radioButton() { 
		//Radio Button Example
		driver.findElement(By.cssSelector("input[value='radio1']")).click();

	}
	@Test
	public void suggestionClass() {
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

	@Test
	// Select Class Example
	public void dropdownExample() {
		WebElement dropdownElement = driver.findElement(By.cssSelector("select#dropdown-class-example"));
		Select dropdown = new Select(dropdownElement);
		// Select option 2 by value, text, or index
		// By value
		dropdown.selectByValue("option2");


	}

	@Test
	// CheckBox Example
	public void checkboxExample() {
		WebElement checkbox = driver.findElement(By.cssSelector("input[id='checkBoxOption1']"));
		checkbox.click();
	}
	@Test
	// switch to New Window Example
	public void newWindow() {
		driver.findElement(By.xpath("//button[@id='openwindow']")).click();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> it = allWindows.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		driver.findElement(By.linkText("Courses")).click();
		WebElement aboutMeElement = driver.findElement(By.cssSelector("div[data-purpose='instructor-description']"));

		// Print the text of the "About me" element
		System.out.println("About me text: " + aboutMeElement.getText());
		driver.close();

		// driver.switchTo().defaultContent(); This is used to switch to the default content

	}
	 
	
	//Switching Tab Example 
	@Test
	public void switchTabExample() {



		WebElement openTab = driver.findElement(By.xpath("//a[@id='opentab' and @class = 'btn-style class1 class2']"));
		openTab.click();
		Set<String> allWindows = driver.getWindowHandles();
		Iterator<String> it = allWindows.iterator();
		String parent = it.next();
		String child = it.next();
		driver.switchTo().window(child);
		driver.findElement(By.xpath("(//a[contains(text(),'Contact')])[1]")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Below is the way to scroll No1
		// js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//div[@class='cont']//p[contains(text(),'Siri Balaji Residency, Road no 2, GaddiAnnaram, Hyderabad, India.')]")));
		WebElement contactInfo = driver.findElement(By.xpath("//div[@class='cont']//p[contains(text(),'Siri Balaji Residency, Road no 2, GaddiAnnaram, Hyderabad, India.')]"));

		Long pageHeight = (Long) js.executeScript("return document.body.scrollHeight");

		//  Below is the way to scroll No2 Scroll to 50% of the page height
		js.executeScript("window.scrollTo(0, " + (pageHeight / 4) + ")");
		System.out.println(contactInfo.getText());
	}

}
