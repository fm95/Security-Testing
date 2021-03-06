package addVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddClass269 {
	
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
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Class");

		tester.setWorkingForm("addclass");

		tester.setHiddenField("fullyear", "1'> <a href=\"https://www.unitn.it\">malicious year</a> <br '");
		tester.setHiddenField("page2", "9'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br '");

		utl.addSubmitButton("//form[@name='addclass']");
		tester.submit();
		tester.assertMatch("Add New Class");
		
		tester.assertLinkNotPresentWithText("malicious year");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
