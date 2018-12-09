package manageVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestManageUsers283 {
	
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
		
		tester.clickLinkWithExactText("Users");
		tester.assertMatch("Manage Users");
		
		tester.setWorkingForm("users");

		tester.setHiddenField("page2", "10' > <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setHiddenField("onpage", "1'> <a href=\"https://www.unitn.it\">malicious onpage</a> <br '");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='users']");
		tester.submit();
		tester.assertMatch("Manage Users");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious onpage");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
}
