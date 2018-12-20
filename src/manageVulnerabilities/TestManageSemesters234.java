package manageVulnerabilities;

import utility.*;
import org.junit.*;

import com.gargoylesoftware.htmlunit.html.DomElement;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.*;

public class TestManageSemesters234 {
	
	private WebTester tester;
	private Utilities utl;
	private String previousValue;
	
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}
	
	@Test
	public void test() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithExactText("Terms");
		tester.assertMatch("Manage Terms");
		tester.setWorkingForm("terms");
		tester.checkCheckbox("delete[]", "1");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Term");
		
		tester.setWorkingForm("editterm");		
		IElement element = tester.getElementByXPath("//input[@name='title']");
		DomElement input = ((HtmlUnitElementImpl) element).getHtmlElement();
		previousValue = input.getAttribute("value");
	
		tester.setTextField("title", "A<a href=\"#\">k");
		tester.clickButtonWithText("Edit Term");
		tester.assertMatch("Manage Terms"); 

		tester.clickLinkWithExactText("Log Out");
		tester.assertMatch("Today's Message");
		
		//-----------------------------------------//
	
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithExactText("Semesters");
		tester.assertMatch("Manage Semesters");
		
		tester.assertLinkNotPresentWithText("k");

		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}
	
	
	@After
	public void cleanUp() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithExactText("Terms");
		tester.assertMatch("Manage Terms");
		tester.setWorkingForm("terms");
		tester.checkCheckbox("delete[]", "1");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Term");
		
		tester.setWorkingForm("editterm");		
		tester.setTextField("title", previousValue);
		tester.clickButtonWithText("Edit Term");
		tester.assertMatch("Manage Terms"); 

		tester.clickLinkWithExactText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}


