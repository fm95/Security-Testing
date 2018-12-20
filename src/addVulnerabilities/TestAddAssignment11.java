package addVulnerabilities;

import utility.Utilities;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddAssignment11 {
	
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
		
		tester.clickLinkWithText("SecTest");
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithText("Assignments");
		tester.assertMatch("Manage Assignments");
		
		tester.setWorkingForm("teacher");
		tester.setHiddenField("page2", "4'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("selectclass", "'> <a href=\"https://www.unitn.it\">malicious selectclass</a> <br'");
		
		utl.addSubmitButton("//form[@name='teacher']");
		tester.submit();
		
		tester.assertMatch("Add New Assignment");
		
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page2");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
