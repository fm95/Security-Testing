package manageVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestManageSemesters268 {
	
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
		
		tester.clickLinkWithExactText("Semesters");
		tester.assertMatch("Manage Semesters");
		
		tester.setWorkingForm("semesters");

		tester.setHiddenField("page2", "5'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setHiddenField("onpage", "1'> <a href=\"https://www.unitn.it\">malicious onpage</a> <br '");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
	
		utl.addSubmitButton("//form[@name='semesters']");
		tester.submit();
		tester.assertMatch("Manage Semesters");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious onpage");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
}
