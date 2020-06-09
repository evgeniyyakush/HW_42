//There is no Implicit or Explicit WaitTime added

package core;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.*;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.HtmlForm;

//We can run HtmlUnit in Linux
// It does not need OS with a Desktop
public class HtmlUnit_P1 {
	static Properties p = new Properties();
	static WebClient driver;

	public static void main(String[] cla) throws Exception {
//		variableExternalization
		p.load(new FileInputStream("./input.properties"));
		
//	    Warnings Off
		Logger.getLogger("").setLevel(Level.OFF);

//		Driver
		driver = new WebClient();
		
//		Browser and OS Version
		System.out.println("Browser: HtmlUnit");
		System.out.println("OS: " + System.getProperty("os.name"));
		
// 		Benchmark start
		final long start = System.currentTimeMillis();

		HtmlPage index_page = driver.getPage(p.getProperty("url"));
		
//		For HtmlUnit
		System.out.println("Page URI: " + index_page.getUrl());
		System.out.println("Page Title: " + index_page.getTitleText());
		
//		For HtmlUnit NEEDS to get form first
		HtmlForm form = index_page.getFormByName(p.getProperty("form"));
		
		
		HtmlTextInput fname = index_page.getHtmlElementById(p.getProperty("fname_id")); fname.setText(p.getProperty("fname_value"));
		HtmlTextInput lname = index_page.getHtmlElementById(p.getProperty("lname_id")); lname.setText(p.getProperty("lname_value"));
		HtmlTextInput email = index_page.getHtmlElementById(p.getProperty("email_id")); email.setText(p.getProperty("email_value"));
		HtmlTextInput phone = index_page.getHtmlElementById(p.getProperty("phone_id")); phone.setText(p.getProperty("phone_value"));
	
		HtmlSubmitInput button = form.getInputByValue("Submit");

		HtmlPage confirmation_page = button.click();
				
		System.out.println("===================================================");
		System.out.println("Page URI: " + confirmation_page.getUrl());
		System.out.println("Page Title: " + confirmation_page.getTitleText());
		
		System.out.println("First Name: " + confirmation_page.getHtmlElementById(p.getProperty("fname_id")).getTextContent().trim());
		System.out.println("Last Name: " + confirmation_page.getHtmlElementById(p.getProperty("lname_id")).getTextContent().trim());
		System.out.println("Email: " + confirmation_page.getHtmlElementById(p.getProperty("email_id")).getTextContent().trim());
		System.out.println("Phone: " + confirmation_page.getHtmlElementById(p.getProperty("phone_id")).getTextContent().trim());

// 		Benchmark finish
        final long finish = System.currentTimeMillis();
		
		driver.close();
		System.out.println("Response time: " + (finish - start) / 1000.0 + " seconds");
	}
}
