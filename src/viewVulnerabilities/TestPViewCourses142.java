package viewVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestPViewCourses142 {
	
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
		
		tester.setWorkingForm("student");
		
		tester.setHiddenField("page2", "5'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "5'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		tester.setHiddenField("student", "1--' /> <a href=\"https://www.unitn.it\">malicious student</a> <br'");
		
		utl.addSubmitButton("//form[@name='student']");
		tester.submit();
		tester.assertMatch("Andrea Pirlo's Classes");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		tester.assertLinkNotPresentWithText("malicious student");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
