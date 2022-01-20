package week4.day1homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/");
		driver.manage().window().maximize();
		String title = driver.getTitle();
		System.out.println(title);
		driver.findElement(By.id("username")).sendKeys("DemoCSR");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		
		//From Contact
		driver.findElement(By.xpath(" //input[@id='partyIdFrom']/following-sibling::a/img")).click();
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		String childWindow = window.get(1);
		String parentWindow = window.get(0);
		
		driver.switchTo().window(childWindow);
		String childTitle = driver.getTitle();
		
		System.out.println(childTitle);
		Thread.sleep(1000);
		
		WebElement fromContact = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
		Thread.sleep(1000);
		String fromText = fromContact.getText();
		System.out.println(fromText);
		fromContact.click();
		driver.switchTo().window(parentWindow);
	
		
		//To Contact
		
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a/img")).click();
		Set<String> windowHandles1 = driver.getWindowHandles();
		List<String> window1 = new ArrayList<String>(windowHandles1);
		String childWindow1 = window1.get(1);
		driver.switchTo().window(childWindow1);
		String childTitle1 = driver.getTitle();
		
		System.out.println(childTitle1);
		Thread.sleep(3000);
		
		WebElement toContact = driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]"));
		Thread.sleep(3000);
		String toText = toContact.getText();
		System.out.println(toText);
		toContact.click();
		
		driver.switchTo().window(parentWindow);
		
		//Merge contact
		
		driver.findElement(By.linkText("Merge")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		String title2 = driver.getTitle();
		System.out.println(title2);
		
		

	}

}
