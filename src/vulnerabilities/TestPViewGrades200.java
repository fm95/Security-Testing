package vulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestPViewGrades200 {
	
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
		
		tester.clickLinkWithText("Luca Abate");
		tester.assertMatch("Luca Abate's Classes");
		
		tester.clickLinkWithText("SecTest");
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithText("Grades");
		tester.assertMatch("View Grades");
		
		tester.setWorkingForm("grades");
		
		tester.setTextField("selectclass", "1--' /> <a href=\"https://www.unitn.it\">malicious selectclass</a> <br");
		tester.setTextField("page2", "3'><a href=\"https://www.unitn.it\">malicious page2</a><br");
		tester.setTextField("page", "5'><a href=\"https://www.unitn.it\">malicious page</a><br");
		
		utl.addSubmitButton("//form[@name='grades']");
		tester.submit();
		tester.assertMatch("View Grades");
		
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
