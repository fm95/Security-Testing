package viewVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestTViewAnnouncements148 {
	
	private WebTester tester;
	private Utilities utl;
	
	
	@Before
	public void prepare() {
		tester = new WebTester();
		utl = new Utilities(tester);
	}
	

	@Test
	public void test() {
		tester = utl.LoginAs("marior", "teacher");
		tester.assertMatch("Mario Rossi's Classes");
		
		tester.clickLinkWithText("SecTest");
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithText("Announcements");
		tester.assertMatch("View Announcements");
		
		tester.setWorkingForm("announcements");
		
		tester.setHiddenField("page2", "9'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("onpage", "1--' /> <a href=\"https://www.unitn.it\">malicious onpage</a> <br'");
		tester.setHiddenField("page", "2'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		
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
