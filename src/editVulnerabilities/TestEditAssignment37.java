package editVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditAssignment37 {

	private WebTester tester;
	private Utilities utl;
	
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.getTestContext().setProxyAuthorization(null, null, "localhost", 8008);
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
		
		tester.setWorkingForm("assignments");
		tester.checkCheckbox("delete[]", "1");

		tester.setTextField("delete[]", "1 -- ' > <a href=\"https://www.unitn.it\">malicious delete</a> <br'");
		tester.setHiddenField("page2", "5'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setHiddenField("selectclass", "1'> <a href=\"https://www.unitn.it\">malicious selectclass</a> <br'");
		tester.setHiddenField("page", "2'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='assignments']");
		tester.submit();
		tester.assertMatch("Edit Assignment");
		
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}

