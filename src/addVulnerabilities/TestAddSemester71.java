package addVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddSemester71 {
	
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
		
		tester.clickLinkWithText("Semesters");
		tester.assertMatch("Manage Semesters");
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Semester");
		
		tester.setWorkingForm("addsemester");
		tester.setHiddenField("page2", "5'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "1'><a href=\"https://www.unitn.it\">malicious page</a><br'");

		utl.addSubmitButton("//form[@name='addsemester']");
		tester.submit();
		tester.assertMatch("Manage Semesters");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
