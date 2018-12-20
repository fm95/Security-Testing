package addVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddStudent70 {
	
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
		
		tester.clickLinkWithText("Students");
		tester.assertMatch("Manage Students");
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Student");
		
		tester.setWorkingForm("addstudent");
		tester.setHiddenField("page2", "20'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "1'><a href=\"https://www.unitn.it\">malicious page</a><br'");

		utl.addSubmitButton("//form[@name='addstudent']");
		tester.submit();
		tester.assertMatch("Add New Student");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
