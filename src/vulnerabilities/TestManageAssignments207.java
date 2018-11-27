package vulnerabilities;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class TestManageAssignments207 {
	
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
		
		tester.setWorkingForm("classes");
		tester.checkCheckbox("delete[]", "1");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Class");
		
		previousValue = tester.getElementTextByXPath("//input[@name='title']");
		tester.setTextField("title", "<a href=\"#\">link</a>");
		
		tester.clickButtonWithText("Edit Class");
		tester.assertMatch("Manage Classes"); 

		tester.clickLinkWithExactText("Log Out");
		tester.assertMatch("Today's Message");
		
		//////////////////////////////////////////
		
		tester = utl.LoginAs("marior", "teacher");
		tester.assertMatch("Mario Rossi's Classes");
		
		tester.clickLinkWithExactText("link", 0);
		tester.assertMatch("Class Settings");
		
		tester.clickLinkWithText("Assignments");
		tester.assertMatch("Manage Assignments");
		
		tester.assertLinkNotPresentWithText("link");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
	}
	
	
	@After
	public void cleanUp() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		tester.setWorkingForm("classes");
		tester.checkCheckbox("delete[]", "1");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Class");
		
		tester.setTextField("title", "SecTest");
		//tester.setTextField("title", previousValue);
		tester.clickButtonWithText("Edit Class");
		tester.assertMatch("Manage Classes"); 
	}
	
	
}
