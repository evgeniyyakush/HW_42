// outputIntoExternalFile: 32
// data being externalized: 48

package core;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Properties;
import java.util.logging.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;

public class HtmlUnit_P2 {
	static Properties p = new Properties();
	static WebClient driver;
	static Writer report;
	//	lineSeparator: to write in the csv file every time from a new line;
	//  using it's the same to make a new line on Windows and Mac
	static String ls = System.getProperty("line.separator");
	
	//------------------------------------------------------------   Main_Method  --------------------------------------------------- //

	public static void main(String[] cla) throws Exception {
//		variableExternalization
		p.load(new FileInputStream("./input.properties"));
		
//		Output into file, false in case we do not want it to create a new file every time. (Do not append, Override)
		//any type of file - txt, csv...
		report = new FileWriter ("./reportHtmlUnit.txt", false);
		
//	    Warnings Off
		Logger.getLogger("").setLevel(Level.OFF);

//		Driver
		driver = new WebClient();
		
//		Browser and OS Version
		//outputIntoConsole
		//System.out.println("Browser: HtmlUnit");
		//System.out.println("OS: " + System.getProperty("os.name")); 
		//System.out.println("==================================================="); 
		
		//outputIntoExternalFile
		report.write("Browser: HtmlUnit"); report.write(ls);
		report.write("OS: " + System.getProperty("os.name")); report.write(ls);
		report.write("==================================================="); report.write(ls);
		
// 		Benchmark start
		final long start = System.currentTimeMillis();

		HtmlPage index_page = driver.getPage(p.getProperty("url"));
		
		//outputIntoConsole
		//System.out.println("Page URI: " + index_page.getUrl());
		//System.out.println("Page Title: " + index_page.getTitleText());
		
		//outputIntoExternalFile
		report.write("Page URI: " + index_page.getUrl()); report.write(ls);
		report.write("Page Title: " + index_page.getTitleText()); report.write(ls);
		
//		For HtmlUnit NEEDS to get form first
		HtmlForm form = index_page.getFormByName(p.getProperty("form"));
		
		
		HtmlTextInput fname = index_page.getHtmlElementById(p.getProperty("fname_id")); fname.setText(p.getProperty("fname_value"));
		HtmlTextInput lname = index_page.getHtmlElementById(p.getProperty("lname_id")); lname.setText(p.getProperty("lname_value"));
		HtmlTextInput email = index_page.getHtmlElementById(p.getProperty("email_id")); email.setText(p.getProperty("email_value"));
		HtmlTextInput phone = index_page.getHtmlElementById(p.getProperty("phone_id")); phone.setText(p.getProperty("phone_value"));
	
		HtmlSubmitInput button = form.getInputByValue("Submit");

		HtmlPage confirmation_page = button.click();
		//outputIntoConsole
		//System.out.println("===================================================");
		//System.out.println("Page URI: " + confirmation_page.getUrl());
		//System.out.println("Page Title: " + confirmation_page.getTitleText());
		//System.out.println("First Name: " + confirmation_page.getHtmlElementById(p.getProperty("fname_id")).getTextContent().trim());
		//System.out.println("Last Name: " + confirmation_page.getHtmlElementById(p.getProperty("lname_id")).getTextContent().trim());
		//System.out.println("Email: " + confirmation_page.getHtmlElementById(p.getProperty("email_id")).getTextContent().trim());
		//System.out.println("Phone: " + confirmation_page.getHtmlElementById(p.getProperty("phone_id")).getTextContent().trim());
		//outputIntoExternalFile
		report.write("===================================================");
		report.write("Page URI: " + confirmation_page.getUrl()); report.write(ls);
		report.write("Page Title: " + confirmation_page.getTitleText()); report.write(ls);
		report.write("First Name: " + confirmation_page.getHtmlElementById(p.getProperty("fname_id")).getTextContent().trim()); report.write(ls);
		report.write("Last Name: " + confirmation_page.getHtmlElementById(p.getProperty("lname_id")).getTextContent().trim()); report.write(ls);
		report.write("Email: " + confirmation_page.getHtmlElementById(p.getProperty("email_id")).getTextContent().trim()); report.write(ls);
		report.write("Phone: " + confirmation_page.getHtmlElementById(p.getProperty("phone_id")).getTextContent().trim()); report.write(ls);
		
		

// 		Benchmark finish
        final long finish = System.currentTimeMillis();
        
//		send all the info from memory into a file and then close it
		report.flush();
		report.close();
		
		driver.close();
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}
