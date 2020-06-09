//Implicit added: 43
//Implicit Nullified: 66
//Implicit added: 80

package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox_P1 {
	static Properties p = new Properties();
	static WebDriver driver;

	//------------------------------------------------------------   Main_Method  --------------------------------------------------- //
	
	public static void main(String[] args) throws Exception {
//		variableExternalization
		p.load(new FileInputStream("./input.properties"));
		
//		Getting rid of Warnings
		Logger.getLogger("").setLevel(Level.OFF);
		String driverPath = "";

//		Running for Windows and MacOS
		if (System.getProperty("os.name").toUpperCase().contains("MAC"))							driverPath = "/usr/local/bin/firefox";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))       driverPath = "c:\\windows\\geckodriver.exe";
		else throw new IllegalArgumentException("Unknown OS");

//		Getting rid of Warnings
		System.setProperty("webdriver.gecko.driver", driverPath);

//		Driver
		driver = new FirefoxDriver();
		
//		implicidWaitTimeStart
		driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);

//		Getting Browser and OS Version
		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
		System.out.println("OS: " + System.getProperty("os.name"));
		System.out.println("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase()
				+ caps.getBrowserName().substring(1).toLowerCase() + " " + caps.getVersion());

		System.out.println("===================================================");

// 		benchmarkStart
		final long start = System.currentTimeMillis();
		
		driver.get(p.getProperty("url"));

		System.out.println("Page URI: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());
		driver.manage().window().maximize();

//		explicidWaitTime
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
//		implicidWaitTime nullifying as we are going to have explicidWaitTime next
		driver.manage().timeouts().implicitlyWait(0,  TimeUnit.SECONDS);
		
		
		/////////////////////////////////////////  Process  /////////////////////////////////////////	
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("fname_id")))).sendKeys(p.getProperty("fname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("lname_id")))).sendKeys(p.getProperty("lname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("email_id")))).sendKeys(p.getProperty("email_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("phone_id")))).sendKeys(p.getProperty("phone_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("submit_id")))).click();
		
		System.out.println("===================================================");
		
//		implicidWaitTimeStart	
		driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);
		
		System.out.println("Page URI: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());
		System.out.println("First Name: " + driver.findElement(By.id(p.getProperty("fname_id"))).getText());
		System.out.println("Last Name: " + driver.findElement(By.id(p.getProperty("lname_id"))).getText());
		System.out.println("Email: " + driver.findElement(By.id(p.getProperty("email_id"))).getText());
		System.out.println("Phone: " + driver.findElement(By.id(p.getProperty("phone_id"))).getText());
		System.out.println();
		
// 		Benchmark finish
		final long finish = System.currentTimeMillis();
		
		driver.quit();
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}
