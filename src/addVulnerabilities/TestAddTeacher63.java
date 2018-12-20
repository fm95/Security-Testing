package addVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddTeacher63 {
	
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
		
		tester.clickLinkWithText("Teachers");
		tester.assertMatch("Manage Teachers");
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Teacher");
		
		tester.setWorkingForm("addteacher");
		tester.setHiddenField("page2", "16'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "1'><a href=\"https://www.unitn.it\">malicious page</a><br'");

		utl.addSubmitButton("//form[@name='addteacher']");
		tester.submit();
		tester.assertMatch("Add New Teacher");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
