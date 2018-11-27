package vulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddTerm19 {
	
	private WebTester tester;
	private Utilities utl;
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}
	
	@Test
	public void test() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithText("Terms");
		tester.assertMatch("Manage Terms");
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Term");
		
		tester.setWorkingForm("addterm");
		// Injection //
		tester.setTextField("page2", "6'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setTextField("page", "1'><a href=\"https://www.unitn.it\">malicious page</a><br'");

		//tester.clickButtonWithText("Add User"); Exception invoking alert
		utl.addSubmitButton("//form[@name='addterm']");
		tester.submit();
		tester.assertMatch("Manage Terms");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
