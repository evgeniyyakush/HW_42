//outputIntoExternalFile: 34
//"trim" added in Safari: 90

package core;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Safari_P2 {
	static Properties p = new Properties();
	static WebDriver driver;
	static Writer report;
	//	lineSeparator: to write in the csv file every time from a new line;
	//  using it's the same to make a new line on Windows and Mac
	static String ls = System.getProperty("line.separator");
	
	//------------------------------------------------------------   Main_Method  --------------------------------------------------- //

	public static void main(String[] args) throws Exception {
//		variableExternalization
		p.load(new FileInputStream("./input.properties"));
		
//		Output into file, false in case we do not want it to create a new file every time. (Do not append, Override)
		//any type of file - txt, csv...
		report = new FileWriter ("./reportSafari.txt", false);
		
//	    Warnings Off
		Logger.getLogger("").setLevel(Level.OFF);

//		Running for Windows and MacOS
		if (!System.getProperty("os.name").contains("Mac OS X")) {
			throw new IllegalArgumentException("Safari is available Only on Mac");}

//		Driver
		driver = new SafariDriver();
		
//		implicidWaitTime start
		driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);

//		Browser and OS Version
		//outputIntoExternalFile
		//writing on new line in full length
		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
		report.write("OS: " + System.getProperty("os.name")); report.write(ls);
		report.write("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase() + caps.getBrowserName().substring(1).toLowerCase() + " " + caps.getVersion()); report.write(ls);
		report.write("==================================================="); report.write(ls);
		
// 		Benchmark start
		final long start = System.currentTimeMillis();
		
		driver.get(p.getProperty("url"));
		
		//outputIntoExternalFile
		report.write("Page URI: " + driver.getCurrentUrl()); report.write(ls);
		report.write("Page Title: " + driver.getTitle()); report.write(ls);
		report.write("==================================================="); report.write(ls);
		
//		Window size
		driver.manage().window().maximize();

//		explicidWaitTime
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
//		Nullify implicidWaitTime 
		driver.manage().timeouts().implicitlyWait(0,  TimeUnit.SECONDS);
		
		/////////////////////////////////////////  Process  /////////////////////////////////////////	
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("fname_id")))).sendKeys(p.getProperty("fname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("lname_id")))).sendKeys(p.getProperty("lname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("email_id")))).sendKeys(p.getProperty("email_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("phone_id")))).sendKeys(p.getProperty("phone_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("submit_id")))).submit();
		wait.until(ExpectedConditions.titleIs("Confirmation"));
		
//		implicidWaitTime start
		driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);
		
		//outputIntoExternalFile
		//"trim" added in Safari
		report.write("Page URI: " + driver.getCurrentUrl().trim()); report.write(ls);
		report.write("Page Title: " + driver.getTitle().trim()); report.write(ls);
		report.write("First Name: " + driver.findElement(By.id(p.getProperty("fname_id"))).getText().trim()); report.write(ls);
		report.write("Last Name: " + driver.findElement(By.id(p.getProperty("lname_id"))).getText().trim()); report.write(ls);
		report.write("Email: " + driver.findElement(By.id(p.getProperty("email_id"))).getText().trim()); report.write(ls);
		report.write("Phone: " + driver.findElement(By.id(p.getProperty("phone_id"))).getText().trim()); report.write(ls);
		report.write(""); report.write(ls);
		
// 		Benchmark finish
		final long finish = System.currentTimeMillis();
		
//		send all the info from memory into a file and then close it
		report.flush();
		report.close();
		
		driver.quit();
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}
