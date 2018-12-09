package editVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditClass239 {

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
		
		tester.setWorkingForm("classes");
		tester.checkCheckbox("delete[]", "1");

		tester.setTextField("delete[]", "1 -- ' > <a href=\"https://www.unitn.it\">malicious delete</a> <br '");
		tester.setHiddenField("page2", "11'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='classes']");
		tester.submit();
		tester.assertMatch("Edit Class");
		
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}


