package myTest;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class Program1 {

	WebDriver driver = new ChromeDriver();
	


	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver","\"E:\\Canada Preparation\\Chrome & Gecko Driver\\chromedriver_win32\\chromedriver.exe");
		String baseUrl = "https://rahulshettyacademy.com/AutomationPractice/";
        
		driver.get(baseUrl);
		driver.manage().window().maximize();
		

	}
	
	@Test (priority = 0, alwaysRun = true)
	public void radioButton() { 
		//Radio Button Example
		driver.findElement(By.cssSelector("input[value='radio1']")).click();

	}
	@Test(priority =1)
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

	@Test(priority =2)
	// Select Class Example
	public void dropdownExample() {
		WebElement dropdownElement = driver.findElement(By.cssSelector("select#dropdown-class-example"));
		Select dropdown = new Select(dropdownElement);
		// Select option 2 by value, text, or index
		// By value
		dropdown.selectByValue("option2");


	}

	@Test(priority =3)
	// CheckBox Example
	public void checkboxExample() {
		WebElement checkbox = driver.findElement(By.cssSelector("input[id='checkBoxOption1']"));
		checkbox.click();
	}
	@Test(priority =4)
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
	@Test(priority =5)
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

	@Test(priority =6)
	public void SwitchToAlertExample() throws InterruptedException {

		driver.findElement(By.cssSelector("input#name.inputs")).sendKeys("Divyank Dawar");
		// Click on the regular alert
		driver.findElement(By.cssSelector("input#alertbtn.btn-style")).click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text: " + alertText);
		alert.accept();

		// How to click the Confirm button 
		WebElement confirmButton = driver.findElement(By.id("confirmbtn"));
		confirmButton.click();
		Alert confirmalert = driver.switchTo().alert();
		Thread.sleep(4000);
		confirmalert.dismiss();


	}

	@Test(priority =7)
	public void webTable() { 

		// Whenever you have to work with webtables you have to find the patters
		// Td means column and Tr means row
		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='product']/tbody/tr"));
		int rowCount = rows.size();

		System.out.println("Total rows in web table: " + rowCount);

		String beforeXpath = "//*[@id=\"product\"]/tbody/tr[";
		String afterXpath =  "]/td[2]";

		for(int i=2;i<=rowCount; i++) {
			String actualXpath = beforeXpath+i+afterXpath;
			WebElement element = driver.findElement(By.xpath(actualXpath));
			System.out.println(element.getText());
			if(element.getText().equals("Master Selenium Automation in simple Python Language")) {
				System.out.println("company name : " + element.getText() + "is found" + "at position: " + (i-1));

				break;			

			}
			//*[@id="product"]/tbody/tr[5]/td[2]
			//*[@id="product"]/tbody/tr[4]/td[2]
			//*[@id="product"]/tbody/tr[1]/th[1]
		}

		//Handle Web tables column 

		String colBeforeXpath = "//*[@id='product']/tbody/tr[1]/th[";
		String colAfterXpath = "]";

		List<WebElement> colums = driver.findElements(By.xpath("//*[@id='product']/tbody/tr[1]/th"));
		int colCount = colums.size();
		System.out.println("Total number of columns are : " + colCount);

		System.out.println("Column values are: " );
		for(int i = 1;i<=colCount;i++) {
			WebElement element = driver.findElement(By.xpath(colBeforeXpath+i+colAfterXpath));
			String colText = element.getText();
			System.out.println(colText);
			System.out.println("Learning git using CMD");
		}

	}


	@Test(priority =8)

	public void mouseHover() throws InterruptedException {


        Actions actions = new Actions(driver);

        // Find the Mouse Hover button using its locator
        WebElement mouseHoverButton = driver.findElement(By.id("mousehover"));

        // Perform a mouse hover action on the Mouse Hover button
        actions.moveToElement(mouseHoverButton).perform();

        // Perform the top Link button 
        WebElement topLink = driver.findElement(By.xpath("//a[text()='Top']"));
		topLink.click();

		actions.moveToElement(mouseHoverButton).perform();

        // Find the Reload button using its locator (assuming it's visible after hover)
        WebElement reloadButton = driver.findElement(By.linkText("Reload"));

        // Click the Reload button
        reloadButton.click();
	}
	 
	@Test(priority =9)
	public void IFrameExample() { 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); 
		driver.switchTo().frame("courses-iframe");

		 
		WebElement jobSupportLink = driver.findElement(By.linkText("Job Support"));
		jobSupportLink.click();
        
		
		WebElement paragraph = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='text'])[1]")));
        System.out.println(paragraph.getText());
        
	}
	
	// Just to Test Commit
	

}

