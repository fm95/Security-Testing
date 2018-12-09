package viewVulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestPViewClassSettings87 {
	
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
		
		tester.clickLinkWithExactText("Andrea Pirlo");
		tester.assertMatch("Andrea Pirlo's Classes");
		
		tester.clickLinkWithExactText("SecTest");
		tester.assertMatch("Class Settings");
				
		tester.setWorkingForm("classes");
		
		tester.setHiddenField("page2", "1'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setHiddenField("page", "5'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		tester.setHiddenField("selectclass", "1--'><a href=\"https://www.unitn.it\">malicious selectclass</a><br'");
		
		utl.addSubmitButton("//form[@name='classes']");
		tester.submit();
		tester.assertMatch("Class Settings");
		
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		tester.assertLinkNotPresentWithText("malicious selectclass");

		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
}
