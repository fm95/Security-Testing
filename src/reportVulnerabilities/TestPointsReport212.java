package reportVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestPointsReport212 {

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
		
		tester.setWorkingForm("students");
		tester.checkCheckbox("delete[]", "1");
		
		tester.setTextField("page2", "32'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setTextField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='students']");
		tester.submit();
		tester.assertMatch("Points Report");

		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}


