package vulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditAnnouncements41 {

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
		
		tester.setWorkingForm("announcements");
		tester.checkCheckbox("delete[]", "1");

		tester.setTextField("delete[]", "1 -- ' > <a href=\"https://www.unitn.it\">malicious delete</a> <br '");
		tester.setTextField("page2", "19'> <a href=\"https://www.unitn.it\">malicious page2</a> <br '");
		tester.setTextField("page", "1'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='announcements']");
		tester.submit();
		tester.assertMatch("Edit Announcement");
		
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}


