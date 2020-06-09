//Output into Excel(external) file
//methods: 28
//explicidWaitTime silenced:147

package core;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.*;

public class Firefox_P3 {
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
			//we could use if you fine 1 Element it's true
			//if (driver.findElements(by).size() == 1 ) 
			return true; 
		else
			return false;}	
	
///////////////////////////////////////////////////////////////////////////////
	//verifyingBySize
	//it's good to verify the presence before the verifying the size
	public static String getSize(By by) {
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//as well the Element IS NOT - Displayed
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
		report = new FileWriter ("./reportFirefox.csv", false);
		
//		Warnings Off
		Logger.getLogger("").setLevel(Level.OFF);
		String driverPath = "";

//		Running for Windows and MacOS
		if (System.getProperty("os.name").toUpperCase().contains("MAC"))							driverPath = "/usr/local/bin/firefox";
		else if (System.getProperty("os.name").toUpperCase().contains("WINDOWS"))       driverPath = "c:\\windows\\geckodriver.exe";
		else throw new IllegalArgumentException("Unknown OS");

//		Warnings Off
		System.setProperty("webdriver.gecko.driver", driverPath);

//		Driver
		driver = new FirefoxDriver();
		
//		implicidWaitTime start
		driver.manage().timeouts().implicitlyWait(15,  TimeUnit.SECONDS);

//		Getting Browser and OS Version
		//outputIntoConsole
		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
		System.out.println("OS: " + System.getProperty("os.name"));
		System.out.println("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase() + caps.getBrowserName().substring(1).toLowerCase() + " " + caps.getVersion());
		System.out.println("***********************");
		
		//outputIntoExternalFile
		//writing on new line in full line
//		Capabilities caps = (((RemoteWebDriver) driver).getCapabilities());
//		String os = System.getProperty("os.name");
//		report.write("OS: " + System.getProperty("os.name")); report.write(ls);
//		report.write("Browser: " + caps.getBrowserName().substring(0, 1).toUpperCase() + caps.getBrowserName().substring(1).toLowerCase() + " " + caps.getVersion()); report.write(ls);
//		report.write("***********************"); report.write(ls);
		
	

// 		Benchmark start
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
		//WebDriverWait wait = new WebDriverWait(driver, 15);
		
//		Nullify implicidWaitTime 
		driver.manage().timeouts().implicitlyWait(0,  TimeUnit.SECONDS);
	
		/////////////////////////////////////////  Process  /////////////////////////////////////////	
		
		//spreadSheet
		//hardCoded_forNow
		//Output into BOTH: CONSOLE AND FILE
	    		  report.write("#,Browser,Page,Field,isPresent,Value"); report.write(ls);
	    System.out.println("#,Browser,Page,Field,isPresent,Value");
	    
	    		  report.write("01,Firefox,index.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + p.getProperty("fname_value")); 								report.write(ls);
	    System.out.println("01,Firefox,index.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + p.getProperty("fname_value"));
	    
	    		  setValue(By.id(p.getProperty("fname_id")),"fname_value");
	    
	              report.write("02,Firefox,index.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + p.getProperty("lname_value")); 									report.write(ls);
	    System.out.println("02,Firefox,index.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + p.getProperty("lname_value"));
	    
	   		  	  setValue(By.id(p.getProperty("lname_id")),"lname_value");
	    
	    		  report.write("03,Firefox,index.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + p.getProperty("email_value"));         									 report.write(ls);
	    System.out.println("03,Firefox,index.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + p.getProperty("email_value"));
	    
	    		  setValue(By.id(p.getProperty("email_id")),"email_value");
	    
	    		  report.write("04,Firefox,index.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + p.getProperty("phone_value"));                                          report.write(ls);
	    System.out.println("04,Firefox,index.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + p.getProperty("phone_value"));
	    
	    		  setValue(By.id(p.getProperty("phone_id")),"phone_value");
	    		  driver.findElement(By.id(p.getProperty("submit_id"))).click();
	    
	    		  report.write("05,Firefox,confirmation.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + getValue(By.id(p.getProperty("fname_id")))); report.write(ls);
	    System.out.println("05,Firefox,confirmation.php,First Name," + isElementPresent(By.id(p.getProperty("fname_id"))) + "," + getValue(By.id(p.getProperty("fname_id"))));
	    
	    		  report.write("06,Firefox,confirmation.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + getValue(By.id(p.getProperty("lname_id")))); report.write(ls);
	    System.out.println("06,Firefox,confirmation.php,Last Name," + isElementPresent(By.id(p.getProperty("lname_id"))) + "," + getValue(By.id(p.getProperty("lname_id"))));
	   
	    		  report.write("07,Firefox,confirmation.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + getValue(By.id(p.getProperty("email_id"))));           report.write(ls);
	    System.out.println("07,Firefox,confirmation.php,Email," + isElementPresent(By.id(p.getProperty("email_id"))) + "," + getValue(By.id(p.getProperty("email_id"))));
	    
	    		  report.write("08,Firefox,confirmation.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + getValue(By.id(p.getProperty("phone_id"))));         report.write(ls);
	    System.out.println("08,Firefox,confirmation.php,Phone," + isElementPresent(By.id(p.getProperty("phone_id"))) + "," + getValue(By.id(p.getProperty("phone_id"))));
		
		
		
/*
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("fname_id")))).sendKeys(p.getProperty("fname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("lname_id")))).sendKeys(p.getProperty("lname_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("email_id")))).sendKeys(p.getProperty("email_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("phone_id")))).sendKeys(p.getProperty("phone_value"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(p.getProperty("submit_id")))).click();
		
		//we also can output into BOTH: CONSOLE AND FILE
		//outputIntoConsole
//		System.out.println("***********************");
//		System.out.println("Page URI: " + driver.getCurrentUrl());
//		System.out.println("Page Title: " + driver.getTitle());
//		System.out.println("First Name: " + driver.findElement(By.id(p.getProperty("fname_id"))).getText());
//		System.out.println("Last Name: " + driver.findElement(By.id(p.getProperty("lname_id"))).getText());
//		System.out.println("Email: " + driver.findElement(By.id(p.getProperty("email_id"))).getText());
//		System.out.println("Phone: " + driver.findElement(By.id(p.getProperty("phone_id"))).getText());
//		System.out.println();
		
		//outputIntoExternalFile
		//writing on new line in full line
		report.write("Page URI: " + driver.getCurrentUrl()); report.write(ls);
		report.write("Page Title: " + driver.getTitle()); report.write(ls);
		report.write("First Name: " + driver.findElement(By.id(p.getProperty("fname_id"))).getText()); report.write(ls);
		report.write("Last Name: " + driver.findElement(By.id(p.getProperty("lname_id"))).getText()); report.write(ls);
		report.write("Email: " + driver.findElement(By.id(p.getProperty("email_id"))).getText()); report.write(ls);
		report.write("Phone: " + driver.findElement(By.id(p.getProperty("phone_id"))).getText()); report.write(ls);
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

