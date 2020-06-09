// //Output into "csv" file: 61
//methods: 28

package core;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.*;

import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
//import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.SilentJavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.html.HtmlForm;

public class HtmlUnit_P4 {
	static Properties p = new Properties();
	static WebClient driver;
	static Writer report;
	//	lineSeparator: to write in the csv file every time from a new line;
	//  using it's the same to make a new line on Windows and Mac
	static String ls = System.getProperty("line.separator");
	
	//------------------------------------------------------------   Methods  -------------------------------------------------------- //
	
///////////////////////////////////////////////////////////////////////////////
	//verifyingPresence
	public static boolean isElementPresentHtmlUnit(HtmlPage page, String by) {
		if(page.getElementsById(by).size() > 0) 
			return true; 
		else 
			return false;}
	
///////////////////////////////////////////////////////////////////////////////
	//settingValue
	public static void setValueHtmlUnit(HtmlPage page, String by, String value) {
		if (isElementPresentHtmlUnit(page, by)) 
		page.getElementById(by).setTextContent(value);}
	
///////////////////////////////////////////////////////////////////////////////
	//gettingValue
	public static String getValueHtmlUnit(HtmlPage page, String by) {
		if (isElementPresentHtmlUnit(page, by)) 
			return page.getElementById(by).getTextContent().trim(); 
		else 
			return "null";}
	
	
	//------------------------------------------------------------   Main_Method  --------------------------------------------------- //

	public static void main(String[] cla) throws Exception {
//		variableExternalization
		p.load(new FileInputStream("./input.properties"));
		
//		Output into file, false in case we do not want it to create a new file every time. (Do not append, Override)
		//any type of file - txt, csv...
		report = new FileWriter ("./reportHtmlUnit.csv", false);
		
//	    Warnings Off
		Logger.getLogger("").setLevel(Level.OFF);

//		Driver
		driver = new WebClient();
		
//	    Warnings Off
		driver.setCssErrorHandler(new SilentCssErrorHandler());
		driver.setJavaScriptErrorListener(new SilentJavaScriptErrorListener());
		
// 		Benchmark start
		final long start = System.currentTimeMillis();

		HtmlPage index_page = driver.getPage(p.getProperty("url"));
		
		//Output into BOTH: CONSOLE AND FILE
		System.out.println("#,Browser,Page,Field,isPresent,Value");
		           report.write("#,Browser,Page,Field,isPresent,Value"); report.write(ls);
		
//		HtmlUnit NEEDS to get form first
		HtmlForm form = index_page.getFormByName(p.getProperty("form"));
		
// 01 :: signUpPage
																									
		           report.write("01,HtmlUnit,index.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("fname_id")) + "," + p.getProperty("fname_value")); report.write(ls);
		System.out.println("01,HtmlUnit,index.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("fname_id")) + "," + p.getProperty("fname_value"));
		//the way I did in the last "HtmlUnit_P2" class
		//                                                                                                        index_page.getHtmlElementById(p.getProperty("fname_id")).setTextContent(p.getProperty("fname_value"));
        //                                                                 HtmlTextInput fname = index_page.getHtmlElementById(p.getProperty("fname_id")); fname.setText(p.getProperty("fname_value"));
																			        setValueHtmlUnit(index_page, p.getProperty("fname_id"), p.getProperty("fname_value"));
																			   
				  report.write("02,HtmlUnit,index.php,Last Name," + isElementPresentHtmlUnit(index_page, p.getProperty("lname_id")) + "," + p.getProperty("lname_value")); report.write(ls);
		System.out.println("02,HtmlUnit,index.php,Last Name," + isElementPresentHtmlUnit(index_page, p.getProperty("lname_id")) + "," + p.getProperty("lname_value"));
											
				setValueHtmlUnit(index_page, p.getProperty("lname_id"), p.getProperty("lname_value"));
																																																													
			  	  report.write("03,HtmlUnit,index.php,Email," + isElementPresentHtmlUnit(index_page, p.getProperty("email_id")) + "," + p.getProperty("email_value")); report.write(ls);
		System.out.println("03,HtmlUnit,index.php,Email," + isElementPresentHtmlUnit(index_page, p.getProperty("email_id")) + "," + p.getProperty("email_value"));
																		
				setValueHtmlUnit(index_page, p.getProperty("email_id"), p.getProperty("email_value"));
																																																											
				  report.write("04,HtmlUnit,index.php,Phone," + isElementPresentHtmlUnit(index_page, p.getProperty("phone_id")) + "," + p.getProperty("phone_value")); report.write(ls);
		System.out.println("04,HtmlUnit,index.php,Phone," + isElementPresentHtmlUnit(index_page, p.getProperty("phone_id")) + "," + p.getProperty("phone_value"));
																					
				setValueHtmlUnit(index_page, p.getProperty("phone_id"), p.getProperty("phone_value"));
	
		HtmlSubmitInput button = form.getInputByValue("Submit");
		HtmlPage confirmation_page = button.click();
		
		//Thread.sleep(1000);

// 02 :: confirmationPage
		
		          report.write("05,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("fname_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("fname_id"))); report.write(ls);
		System.out.println("05,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("fname_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("fname_id")));
		
        		  report.write("06,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("lname_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("lname_id"))); report.write(ls);
        System.out.println("06,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("lname_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("lname_id")));

				  report.write("07,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("email_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("email_id"))); report.write(ls);
		System.out.println("07,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("email_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("email_id")));

				  report.write("08,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("phone_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("phone_id"))); report.write(ls);
		System.out.println("08,HtmlUnit,confirmation.php,First Name," + isElementPresentHtmlUnit(index_page, p.getProperty("phone_id")) + "," + getValueHtmlUnit(confirmation_page, p.getProperty("phone_id")));
		
	
// 		Benchmark finish
        final long finish = System.currentTimeMillis();
        
//		send all the info from memory into a file and then close it
		report.flush();
		report.close();
		
		driver.close();
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}
