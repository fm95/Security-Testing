package manageVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestManageSemesters234 {
	
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
		tester.checkCheckbox("delete[]", "1");

		tester.setTextField("delete[]", "1 -- ' > <a href=\"https://www.unitn.it\">malicious delete</a> <br '");
			
		utl.addSubmitButton("//form[@name='semesters']");
		tester.submit();
		tester.assertMatch("Manage Semesters");
		
		tester.assertLinkNotPresentWithText("malicious delete");

		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
}
