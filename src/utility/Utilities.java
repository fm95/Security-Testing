package utility;

import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.*;

/**
* This Utilities class implements a set of utilities, like the login and 
* the cleaner function, useful for automate the testing of Web Application with
* JWebUnit.
*
* @author  Francesco Minna 
* @version 1.0
* @since   2018-11
*/

public class Utilities {
	
	private WebTester tester;
	private String URL, page, username, password, previousValue;
	
	// Constructor //
	public Utilities(WebTester tester) {
		this.tester = tester;
		this.URL = "http://localhost/schoolmate/";
		
		this.tester.setBaseUrl(URL);
	}
	
	/**
	 *  Login in the default page: index.php //
	 * @param username
	 * @param password
	 * @return NULL --> it calls the next overwritten function
	 */
	public WebTester LoginAs(String username, String password) {
		LoginAs("index.php", username, password);
		return this.tester; //escape error of return value
	}

	/**
	 *  Login to the WebApp //
	 * @param page
	 * @param username
	 * @param password
	 * @return the WebTester with all the parameters sets
	 */
	public WebTester LoginAs(String page, String username, String password) {
		this.username = username;
		this.password = password;
		this.page = page;
		
		this.tester.beginAt(page);
		this.tester.setTextField("username", username);
		this.tester.setTextField("password", password);
		this.tester.submit();
		
		return this.tester;
	}
	
	/**
	 *  Save the original database status (pre-exploit) //
	 * @param xpath
	 */
	public void SetPreviousValue(String xpath) {
		this.previousValue = this.tester.getElementByXPath(xpath).getTextContent();
	}
	
	/**
	 *  Restore the database to the original state //
	 */
	public void clean() {
		this.tester = this.LoginAs(page, username, password);
		
		//
		tester.setTextField("blabla", this.previousValue); //check if all are text area
		//
		
		this.tester.clickLinkWithExactText("Log Out");
	}
	
	/**
	 * Return the previous database value
	 * @return String value
	 */
	public String getPreviousValue() {
		return this.previousValue;
	}
	
	/**
	 * Add submit path to escape form's javascript function (e.g. onclick)
	 * @param xpath of the form 
	 */
	public void addSubmitButton(String xpath) {
		IElement element = tester.getElementByXPath(xpath);
		DomElement form = ((HtmlUnitElementImpl) element).getHtmlElement();		
		InputElementFactory factory = InputElementFactory.instance;
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("","","type","","submit");
		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
		form.appendChild(submit);
	}
	
	/**
	 *  Change default URL 
	 * @param URL
	 */
	public void changeURL(String URL) {
		this.URL = URL;
	}
	
}
