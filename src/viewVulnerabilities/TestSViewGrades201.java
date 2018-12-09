package viewVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestSViewGrades201 {
	
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
		
		tester.clickLinkWithText("SecTest");
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithText("Grades");
		tester.assertMatch("View Grades");
		
		tester.setWorkingForm("grades");
		
		tester.setHiddenField("selectclass", "1--' /> <a href=\"https://www.unitn.it\">malicious selectclass</a> <br");
		tester.setHiddenField("page2", "3'><a href=\"https://www.unitn.it\">malicious page2</a><br");
		tester.setHiddenField("page", "4'><a href=\"https://www.unitn.it\">malicious page</a><br");
		
		utl.addSubmitButton("//form[@name='grades']");
		tester.submit();
		tester.assertMatch("View Grades");
		
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
