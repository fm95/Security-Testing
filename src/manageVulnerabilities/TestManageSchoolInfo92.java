package manageVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestManageSchoolInfo92 {
	
	private WebTester tester;
	private Utilities utl;
	//private String previousAddress, previousPhone;
	
	
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
		
		tester.clickLinkWithExactText("School");
		tester.assertMatch("Manage School Information");
		
		tester.setWorkingForm("info");
		
		// Backup
		//previousAddress = tester.getElementByXPath("//input[@name='schooladdress']").getTextContent();; 
		//previousPhone = tester.getElementByXPath("//input[@name='schoolphone']").getTextContent();;
		
		//tester.setTextField("schooladdress", "Str' > <a href=\"#\">link1</a>");
		//tester.setTextField("schoolphone", "9'><a>link</a>"); // maxlength = 14
		tester.setHiddenField("page2", "1'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");		
		tester.clickButtonWithText(" Update ");
		tester.assertMatch("Manage School Information");
		
		//tester.assertLinkNotPresentWithText("link1");
		//tester.assertLinkNotPresentWithText("link");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	/*
	@After
	public void clean() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithExactText("School");
		tester.assertMatch("Manage School Information");
		
		tester.setWorkingForm("info");
		tester.setTextField("schooladdress", previousAddress);
		tester.setTextField("schoolphone", previousPhone);
		tester.clickButtonWithText(" Update ");
		tester.assertMatch("Manage School Information");

		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	*/
	
}
