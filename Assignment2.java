package week4.day1homework;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment2 {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev106849.service-now.com");		
		driver.manage().window().maximize();	
		
		driver.switchTo().frame(0);
		
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("$uMant#1835");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("incident");
		
		System.out.println("check incident");
		
		//driver.switchTo().parentFrame();
		
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//a[@id='b55b4ab0c0a80009007a9c0f03fb4da9']//div[text()='All']")).click();
		
		System.out.println("check All");
		
			
		Thread.sleep(3000);
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("sysverb_new")).click();
		
		//driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("lookup.incident.caller_id")).click();
		
		System.out.println("check call");
		
		//get windowHandles
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> childWindow = new ArrayList<String>(windowHandles);
		
		String chW = childWindow.get(1);
		String paW = childWindow.get(0);
		
		//Switching to child window
		driver.switchTo().window(chW);
		driver.findElement(By.linkText("Creator User")).click();
		
		//Switching to parent window
		
		driver.switchTo().window(paW);
		
		//Switching to main frame
		driver.switchTo().frame("gsft_main");
		
		driver.findElement(By.id("incident.short_description")).sendKeys("Test Description");
		String incident = driver.findElement(By.id("incident.number")).getAttribute("value");
		System.out.println("Incident created "+": "+incident);
		
		driver.findElement(By.id("sysverb_insert")).click();		
		
		WebElement search = driver.findElement(By.xpath("//input[@class='form-control']"));
		search.sendKeys(incident);
		search.sendKeys(Keys.ENTER);
		
		Thread.sleep(3000);
		
		String text = driver.findElement(By.xpath("//td[@class='vt']/a[1]")).getText();
		
		System.out.println("Incident fetched "+": "+text);
		
		if (text.equals(incident)) {
			
			System.out.println("Incident created successfully");
			
		} else {
			
			System.out.println("No incident found");

		}
		
		File screenshotAs = driver.getScreenshotAs(OutputType.FILE);
		File Destination = new File("./images/incident.png");
		FileUtils.copyFile(screenshotAs, Destination);
		
		
	}
	

}
