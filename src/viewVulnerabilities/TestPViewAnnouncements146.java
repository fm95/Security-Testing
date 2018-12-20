package viewVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestPViewAnnouncements146 {
	
	private WebTester tester;
	private Utilities utl;
	
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}
	
	
	@Test
	public void test() {
		tester = utl.LoginAs("fabioc", "parent");
		tester.assertMatch("Students of Fabio Cannavaro");
		
		tester.clickLinkWithText("Andrea Pirlo");
		tester.assertMatch("Andrea Pirlo's Classes");
		
		tester.clickLinkWithText("SecTest");
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithText("Announcements");
		tester.assertMatch("View Announcements");
		
		tester.setWorkingForm("announcements");
	
		tester.setHiddenField("page2", "4'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("onpage", "1--' /> <a href=\"https://www.unitn.it\">malicious onpage</a> <br'");
		tester.setHiddenField("page", "5'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		
		utl.addSubmitButton("//form[@name='announcements']");
		tester.submit();
		tester.assertMatch("View Announcements");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious onpage");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
