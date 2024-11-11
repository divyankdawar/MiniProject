package myTest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;

public class Base {
	
	public static WebDriver driver;
	
//	WebDriver driver = new ChromeDriver();
	
	
	public static void initialization()
	{
		System.setProperty("webdriver.chrome.driver","E:\\Canada Preparation\\Chrome & Gecko Driver\\chromedriver_win32\\chromedriver.exe");
		String baseUrl = "https://rahulshettyacademy.com/AutomationPractice/";
        driver = new ChromeDriver();
		driver.get(baseUrl);
		driver.manage().window().maximize();


	}
	
	public void failed(String testMethodName) {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File("E:\\Canada Preparation\\CodeWithHarryJAVA\\Java CWH\\MiniProject\\Screenshots\\"+"failshot_"+testMethodName+"_"+".jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
