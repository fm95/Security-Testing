package manageVulnerabilities;

import utility.*;
import org.junit.*;

import com.gargoylesoftware.htmlunit.html.DomElement;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.*;

public class TestManageAssignments207 {
	
	private WebTester tester;
	private Utilities utl;
	String previousValue;
	
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}

	@Test
	public void test() {
		
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.setWorkingForm("classes");
		tester.checkCheckbox("delete[]", "1");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Class");
		
		tester.setWorkingForm("editclass");	
		
		IElement element = tester.getElementByXPath("//input[@name='title']");
		DomElement input = ((HtmlUnitElementImpl) element).getHtmlElement();
		previousValue = input.getAttribute("value");
		
		tester.setTextField("title", "<a href=\"#\">k</a>");
		tester.clickButtonWithText("Edit Class");
		tester.assertMatch("Manage Classes"); 
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
				
		////////////////////////////////////////////
		
		tester = utl.LoginAs("marior", "teacher");
		tester.assertMatch("Mario Rossi's Classes");
		
		tester.clickLinkWithExactText("k");
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithExactText("Assignments");
		tester.assertMatch("Manage Assignments"); 
		
		tester.assertLinkNotPresentWithText("k");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
	@After
	public void cleanUp() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.setWorkingForm("classes");
		tester.checkCheckbox("delete[]", "1");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Class");
		tester.setWorkingForm("editclass");
		tester.setTextField("title", previousValue);
		tester.clickButtonWithText("Edit Class");
		tester.assertMatch("Manage Classes"); 
		
		tester.clickLinkWithExactText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
