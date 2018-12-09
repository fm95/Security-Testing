package visualizeVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestVisualizeRegistration238 {
	
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
		
		tester.clickLinkWithText("Registration");
		tester.assertMatch("Registration");
		
		tester.setWorkingForm("registration");
		tester.setHiddenField("page2", "29'> <a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a><br'");
		
		utl.addSubmitButton("//form[@name='registration']");
		tester.submit();
		tester.assertMatch("Andrea Pirlo's Schedule");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");

		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}
	
	
}
