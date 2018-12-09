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
	
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}
	
	@Test
	public void test() {
		tester = utl.LoginAs("marior", "teacher");
		tester.assertMatch("Mario Rossi's Classes");
		
		tester.clickLinkWithExactText("SecTest");
		tester.assertMatch("Class Settings");
		
		tester.setWorkingForm("teacher");
		
		tester.setHiddenField("page2", "2");
		tester.setHiddenField("page", "2");
		tester.setHiddenField("selectclass", "1 -- ' -- > <a href=\"https://unitn.it\">malicious selectclass</a> <br '");
		
		utl.addSubmitButton("//form[@name='teacher']");
		tester.submit();
		tester.assertMatch("Manage Assignments"); 
		
		tester.assertLinkNotPresentWithText("malicious selectclass");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
