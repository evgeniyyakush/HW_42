//Output into Excel(external) file: 88
//methods: 26
//explicidWaitTime silenced:145

package core;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.support.ui.ExpectedConditions;

public class Safari_P3 {
	static Properties p = new Properties();
	static WebDriver driver;
	static Writer report;
	//	lineSeparator: to write in the csv file every time from a new line;
	static String ls = System.getProperty("line.separator");
	
	//------------------------------------------------------------   Methods  -------------------------------------------------------- //
	
///////////////////////////////////////////////////////////////////////////////
	//verifyingPresence
	public static boolean isElementPresent(By by) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty()) 
			//finding just one Element would say it exists
			//if (driver.findElements(by).size() == 1 ) 
			return true; 
		else
			return false;}	
	
///////////////////////////////////////////////////////////////////////////////
	//verifyingBySize
	//it's good to verify the presence before the verifying the size
	public static String getSize(By by) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//also Element IS NOT - Displayed
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			return driver.findElement(by).getRect().getDimension().toString(); 
		else 
			// else? - return "Emptiness"
			return "";}
	
///////////////////////////////////////////////////////////////////////////////
	//verifyingByLocation
	public static String getLocation(By by) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			return driver.findElement(by).getRect().getPoint().toString(); 
		else 
			return "";}
	
///////////////////////////////////////////////////////////////////////////////
	//settingValue
	public static void setValue(By by, String value) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			driver.findElement(by).sendKeys(p.getProperty(value));}

///////////////////////////////////////////////////////////////////////////////
	//gettingValue
	public static String getValue(By by) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		if (!driver.findElements(by).isEmpty() && driver.findElement(by).isDisplayed())
			return driver.findElement(by).getText(); 
		else 
			return "";}
	
	
	
	//------------------------------------------------------------   Main_Method  --------------------------------------------------- //
																		              

	public static void main(String[] args) throws Exception {
		
        /////////////////////////////////////////  Prerequisites  /////////////////////////////////////////	
//		variableExternalization
		p.load(new FileInputStream("./input.properties"));
		
//		Output into file, false in case we do not want it to create a new file every time. (Do not append, Override)
		report = new FileWriter ("./reportSafari.csv", false);
		
//		Warnings Off
		Logger.getLogger("").setLevel(Level.OFF);

//		Running for Windows and MacOS
		if (!System.getProperty("os.name").contains("Mac OS X")) {
			throw new IllegalArgumentException("Safari is available Only on Mac");}

//		Driver
		driver = new SafariDriver();
		
//		implicidWaitTime start
		driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);

//		Getting Browser and OS Version
		//outputIntoConsole
		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
		System.out.println("OS: " + System.getProperty("os.name"));
		System.out.println("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase() + caps.getBrowserName().substring(1).toLowerCase() + " " + caps.getVersion());
		System.out.println("***********************");
		
		
		//outputIntoExternalFile
		//writing on new line in full line by using "ls" variable - Line Separator
//		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
//		String os = System.getProperty("os.name");
//		report.write("OS: " + System.getProperty("os.name")); report.write(ls);
//		report.write("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase() + caps.getBrowserName().substring(1).toLowerCase() + " " + caps.getVersion()); report.write(ls);
//		report.write("***********************"); report.write(ls);
		
		
// 		benchmarkStart
		final long start = System.currentTimeMillis();
		
		driver.get(p.getProperty("url"));

		//outputIntoConsole
		System.out.println("Page URI: " + driver.getCurrentUrl());
		System.out.println("Page Title: " + driver.getTitle());
		System.out.println("***********************");
		
		//outputIntoExternalFile
//		report.write("Page URI: " + driver.getCurrentUrl()); report.write(ls);
//		report.write("Page Title: " + driver.getTitle()); report.write(ls);
//		report.write("***********************"); report.write(ls);
		
//		Window size
		//driver.manage().window().maximize();
		driver.manage().window().setSize(new Dimension(1680, 1050));

//		explicidWaitTime
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
//		Nullify implicidWaitTime 
		driver.manage().timeouts().implicitlyWait(0,  TimeUnit.SECONDS);
	
        /////////////////////////////////////////  Process  /////////////////////////////////////////	
		
		//spreadSheet
		//hardCoded_forNow
		//Output into BOTH: CONSOLE AND FILE
		  report.write("#,Browser,Page,Field,isPresent,Value"); report.write(ls);
System.out.println("#,Browser,Page,Field,isPresent,Value");

		  report.write("01,Safari,index.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + p.getProperty("fname_value")); 												report.write(ls);
System.out.println("01,Safari,index.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + p.getProperty("fname_value"));

		  setValue(By.id(p.getProperty("fname_id")),"fname_value");

        report.write("02,Safari,index.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + p.getProperty("lname_value")); 													report.write(ls);
System.out.println("02,Safari,index.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + p.getProperty("lname_value"));

		  	  setValue(By.id(p.getProperty("lname_id")),"lname_value");

		  report.write("03,Safari,index.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + p.getProperty("email_value"));         												 report.write(ls);
System.out.println("03,Safari,index.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + p.getProperty("email_value"));

		  setValue(By.id(p.getProperty("email_id")),"email_value");

		  report.write("04,Safari,index.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + p.getProperty("phone_value"));                              			            report.write(ls);
System.out.println("04,Safari,index.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + p.getProperty("phone_value"));

		  setValue(By.id(p.getProperty("phone_id")),"phone_value");
		  driver.findElement(By.id(p.getProperty("submit_id"))).submit();
		  // Added for Safari
		  wait.until(ExpectedConditions.titleIs("Confirmation"));
		  
		  /////////////////////////////////////
		  //"trim" added for Safari to have it on the same line
		  report.write("05,Safari,confirmation.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + getValue(By.id(p.getProperty("fname_id"))).trim()); report.write(ls);
System.out.println("05,Safari,confirmation.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + getValue(By.id(p.getProperty("fname_id"))).trim());

		  report.write("06,Safari,confirmation.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + getValue(By.id(p.getProperty("lname_id"))).trim()); report.write(ls);
System.out.println("06,Safari,confirmation.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + getValue(By.id(p.getProperty("lname_id"))).trim());

		  report.write("07,Safari,confirmation.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + getValue(By.id(p.getProperty("email_id"))).trim());           report.write(ls);
System.out.println("07,Safari,confirmation.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + getValue(By.id(p.getProperty("email_id"))).trim());

		  report.write("08,Safari,confirmation.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + getValue(By.id(p.getProperty("phone_id"))).trim());         report.write(ls);
System.out.println("08,Safari,confirmation.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + getValue(By.id(p.getProperty("phone_id"))).trim());
		
		
		
/*
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("fname_id")))).sendKeys(p.getProperty("fname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("lname_id")))).sendKeys(p.getProperty("lname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("email_id")))).sendKeys(p.getProperty("email_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("phone_id")))).sendKeys(p.getProperty("phone_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("submit_id")))).submit();
		wait.until(ExpectedConditions.titleIs("Confirmation"));
		
		//we also can output into BOTH: CONSOLE AND FILE
		//outputIntoConsole
//		System.out.println("***********************");
		System.out.println("Page URI: " + driver.getCurrentUrl().trim());
		System.out.println("Page Title: " + driver.getTitle().trim());
		System.out.println("First Name: " + driver.findElement(By.id(p.getProperty("fname_id"))).getText().trim());
		System.out.println("Last Name: " + driver.findElement(By.id(p.getProperty("lname_id"))).getText().trim());
		System.out.println("Email: " + driver.findElement(By.id(p.getProperty("email_id"))).getText().trim());
		System.out.println("Phone: " + driver.findElement(By.id(p.getProperty("phone_id"))).getText().trim());
//		System.out.println();
		
		//outputIntoExternalFile
		//writing on new line in full line
		report.write("Page URI: " + driver.getCurrentUrl().trim()); report.write(ls);
		report.write("Page Title: " + driver.getTitle().trim()); report.write(ls);
		report.write("First Name: " + driver.findElement(By.id(p.getProperty("fname_id"))).getText().trim()); report.write(ls);
		report.write("Last Name: " + driver.findElement(By.id(p.getProperty("lname_id"))).getText().trim()); report.write(ls);
		report.write("Email: " + driver.findElement(By.id(p.getProperty("email_id"))).getText().trim()); report.write(ls);
		report.write("Phone: " + driver.findElement(By.id(p.getProperty("phone_id"))).getText().trim()); report.write(ls);
		report.write(""); report.write(ls);
*/		
		
		
		
// 		Benchmark finish
		final long finish = System.currentTimeMillis();
		
//		send all the info from memory into a file and then close it
		report.flush();
		report.close();
		
		driver.quit();
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}

