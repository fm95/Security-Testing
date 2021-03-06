package otherVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestClassSettings89 {

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
		tester.setHiddenField("page2", "1'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "2'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		tester.setHiddenField("selectclass", "1--'><a href=\"https://www.unitn.it\">malicious selectclass</a><br'");

		utl.addSubmitButton("//form[@name='teacher']");
		tester.submit();
		tester.assertMatch("Class Settings");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		tester.assertLinkNotPresentWithText("malicious selectclass");
	
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
