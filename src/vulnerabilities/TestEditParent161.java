package vulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditParent161 {

	private WebTester tester;
	private Utilities utl;
	
	@Before
	public void prepare() {
		tester = new WebTester();
		//tester.getTestContext().setProxyAuthorization(null, null, "localhost", 8008);
		utl = new Utilities(tester);
	}
	
	@Test
	public void test() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithText("Parents");
		tester.assertMatch("Manage Parents");
		
		tester.setWorkingForm("parents");
		tester.checkCheckbox("delete[]", "1");

		tester.setTextField("delete[]", "1--' > <a href=\"https://www.unitn.it\">malicious delete</a> <br '");
		tester.setTextField("page2", "24'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setTextField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='parents']");
		tester.submit();
		tester.assertMatch("Edit Parent");
		
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}


