package otherVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.*;
import utility.Utilities;


public class TestSiteMessage54 {

	private WebTester tester;
	private Utilities utl;
	private String previousValue;

	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}

	
	@Test
	public void test() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithText("School");
		tester.assertMatch("Manage School Information");
		previousValue = tester.getElementByXPath("html//textarea[@name='sitetext']").getTextContent();
		
		tester.setTextField("sitetext", "original message <a href=\"https://www.unitn.it\">malicious sitetext</a>");
		tester.clickButtonWithText(" Update ");

		tester.clickLinkWithExactText("Log Out");
		tester.assertMatch("Today's Message");
		
		tester.assertLinkNotPresentWithText("malicious sitetext");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
				
	}
	
	@After
	public void cleanUp() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithText("School");
		tester.assertMatch("Manage School Information");
		
		tester.setTextField("sitetext", previousValue);
		tester.clickButtonWithText(" Update ");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
}
