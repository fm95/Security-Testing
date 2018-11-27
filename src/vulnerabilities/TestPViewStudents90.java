package vulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestPViewStudents90 {
	
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
		tester = utl.LoginAs("fabioc", "parent");
		tester.assertMatch("Students of Fabio Cannavaro");
		
		tester.setWorkingForm("student");
		
		tester.setTextField("page2", "0'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setTextField("page", "5'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		
		utl.addSubmitButton("//form[@name='student']");
		tester.submit();
		tester.assertMatch("Students of Fabio Cannavaro");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
