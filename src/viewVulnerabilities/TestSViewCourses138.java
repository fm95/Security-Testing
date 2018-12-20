package viewVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestSViewCourses138 {
	
	private WebTester tester;
	private Utilities utl;
	
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}
	
	
	@Test
	public void test() {
		tester = utl.LoginAs("andreap", "student");
		tester.assertMatch("Andrea Pirlo's Classes");
		
		tester.clickLinkWithText("Classes");
		tester.assertMatch("Andrea Pirlo's Classes");
				
		tester.setWorkingForm("student");	
		tester.setHiddenField("page2", "0'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "4'><a href=\"https://www.unitn.it\">malicious page</a><br'");
	
		utl.addSubmitButton("//form[@name='student']");
		tester.submit();
		tester.assertMatch("Andrea Pirlo's Classes");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");

		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
