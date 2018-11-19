package it.unitn.testing;

import org.junit.*;
import net.sourceforge.jwebunit.junit.*;



public class TestSiteMessage54 {

	private WebTester tester;
	private String previousValue;

	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost/schoolmate");
	}

	@Test
	public void test() {
		tester.beginAt("/index.php");
		tester.setTextField("username", "schoolmate");
		tester.setTextField("password", "schoolmate");
		tester.submit();
		
		//After i login as schoolmate
		tester.clickLinkWithText("School");
		tester.assertMatch("Manage School Information");
		previousValue = tester.getElementByXPath("html//textarea[@name='sitetext']").getTextContent();
		
		tester.setTextField("sitetext", "original message <a href=\"https://www.unitn.it\">malicious link</a>");
		tester.clickButtonWithText(" Update ");
		
		// After injection
		tester.clickLinkWithExactText("Log Out");
		
		tester.assertMatch("Today's Message");
		// check injection
		tester.assertLinkNotPresentWithText("malicious link");
		// if the vulnerability is present, the test must fail!
		
	}
	
	@After
	public void cleanUp() {
		tester.beginAt("/index.php");
		tester.setTextField("username", "schoolmate");
		tester.setTextField("password", "schoolmate");
		tester.submit();
		
		tester.clickLinkWithText("School");
		tester.assertMatch("Manage School Information");
		
		tester.setTextField("sitetext", previousValue);
		tester.clickButtonWithText(" Update ");
		
		tester.clickLinkWithExactText("Log Out");
		
	}
	
}
