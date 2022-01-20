package week4.day1homework;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment4 {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leafground.com/pages/frame.html");
		driver.manage().window().maximize();
		driver.switchTo().frame(0);
		
		//SCreenshot
		WebElement clickMe = driver.findElement(By.xpath(" //button[text()='Click Me']"));
		File screenshotAs = clickMe.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/click.png");
		FileUtils.copyFile(screenshotAs, destination);
		
		driver.switchTo().defaultContent();
	

		//No of frames
		List<WebElement> findElements = driver.findElements(By.tagName("iframe"));
		Thread.sleep(3000);
		int size = findElements.size();
		System.out.println(size);
	}

}
