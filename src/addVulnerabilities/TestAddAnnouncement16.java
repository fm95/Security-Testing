package addVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestAddAnnouncement16 {
	
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
		
		tester.clickLinkWithText("Announcements");
		tester.assertMatch("Manage Announcements");
		
		tester.clickButtonWithText("Add");
		tester.assertMatch("Add New Announcement");
		
		tester.setWorkingForm("addannouncement");
		tester.setHiddenField("page2", "4'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setHiddenField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");

		utl.addSubmitButton("//form[@name='addannouncement']");
		tester.submit();
		tester.assertMatch("Manage Announcements");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	

}
