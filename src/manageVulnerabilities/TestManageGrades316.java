package manageVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestManageGrades316 {
	
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
		
		tester.clickLinkWithExactText("Grades");
		tester.assertMatch("Grades");
		
		tester.setWorkingForm("grades");

		tester.setHiddenField("page2", "3'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setHiddenField("selectclass", "1' > <a href=\"https://www.unitn.it\">malicious selectclass</a> <br '");
		tester.setHiddenField("page", "2'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='grades']");
		tester.submit();
		tester.assertMatch("Grades");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
}
