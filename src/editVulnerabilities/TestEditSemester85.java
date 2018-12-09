package editVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditSemester85 {

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
		
		tester.setWorkingForm("semesters");
		tester.checkCheckbox("delete[]", "2");

		tester.setTextField("delete[]", "2 -- ' > <a href=\"https://www.unitn.it\">malicious delete</a> <br'");
		tester.setHiddenField("page2", "13'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='semesters']");
		tester.submit();
		tester.assertMatch("Edit Semester");
		
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}

