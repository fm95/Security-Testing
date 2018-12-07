package vulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditGrade76 {

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
		
		tester.clickLinkWithText("Grades");
		tester.assertMatch("Grades");
		
		tester.setWorkingForm("grades");
		tester.checkCheckbox("delete[]", "1");
		
		
		// NOT WORKING //
		
		tester.selectOptionByValue("assignment", "1");
		//tester.selectOptionByValue("assignment", "1--'--  > <a href=\"https://www.unitn.it\">malicious assignment</a> <br'");
		//tester.setTextField("assignment", "1--'--  > <a href=\"https://www.unitn.it\">malicious assignment</a> <br'");
		
		/////////////////
		
		tester.setTextField("delete[]", "1'-- > <a href=\"https://www.unitn.it\">malicious delete</a> <br'");
		tester.setTextField("page2", "7'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setTextField("selectclass", "1'> <a href=\"https://www.unitn.it\">malicious selectclass</a> <br'");
		tester.setTextField("page", "2'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='grades']");
		tester.submit();
		tester.assertMatch("Edit Grade");
		
		tester.assertLinkNotPresentWithText("malicious assignment");
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}

