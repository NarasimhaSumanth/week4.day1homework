package week4.day1homework;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HandleWindows {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		String parentTitle = driver.getTitle();
		System.out.println(parentTitle);
		
		driver.findElement(By.xpath(" //button[text()='Wait for 5 seconds']")).click();
		
		Thread.sleep(6000);
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		String childOne = window.get(1);
		driver.switchTo().window(childOne);
		String childOneTitle = driver.getTitle();
		System.out.println(childOneTitle);	

	}

}
