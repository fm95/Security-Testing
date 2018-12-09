package addVulnerabilities;

import utility.Utilities;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddAttendance13 {
	
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
		
		tester.clickLinkWithText("Attendance");
		tester.assertMatch("Attendance");
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Attendance Record");

		tester.setWorkingForm("addattendance");
		
		tester.setHiddenField("semester", "1'> <a href=\"https://unitn.it\">malicious semester</a> <br");
		tester.setHiddenField("student", "2'> <a href=\"https://www.unitn.it\">malicious student</a> <br");
		tester.setHiddenField("page2", "31'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='addattendance']");
		tester.submit();
		tester.assertMatch("Attendance");
		
		tester.assertLinkNotPresentWithText("malicious semester");
		tester.assertLinkNotPresentWithText("malicious student");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
