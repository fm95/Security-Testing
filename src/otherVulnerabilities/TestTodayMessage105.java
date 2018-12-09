package otherVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.*;
import utility.Utilities;


public class TestTodayMessage105 {

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
		
		previousValue = tester.getElementByXPath("html//textarea[@name='sitemessage']").getTextContent();
		
		tester.setTextField("sitemessage", "Hi <a href=\"https://unitn.it\">malicious sitemessage</a>");
		tester.clickButtonWithText(" Update ");

		tester.clickLinkWithExactText("Log Out");
		tester.assertMatch("Today's Message");
		
		tester.assertLinkNotPresentWithText("malicious sitemessage");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	@After
	public void cleanUp() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.clickLinkWithText("School");
		tester.assertMatch("Manage School Information");
		
		tester.setTextField("sitemessage", previousValue);
		tester.clickButtonWithText(" Update ");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
}
