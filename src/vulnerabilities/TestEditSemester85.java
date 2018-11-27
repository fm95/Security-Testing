package vulnerabilities;

import org.junit.*;

import com.gargoylesoftware.htmlunit.html.DomElement;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditSemester85 {

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
		
		tester.clickLinkWithText("Semesters");
		tester.assertMatch("Manage Semesters");
		
		tester.setWorkingForm("semesters");
		tester.checkCheckbox("delete[]", "2");
		tester.clickButtonWithText("Edit");
		tester.assertMatch("Edit Semester");
		
		tester.setWorkingForm("editsemester");
		
		tester.setTextField("semesterid", "2' > <a href=\"https://www.unitn.it\">malicious semesterid</a> ");
		tester.setTextField("page2", "13'><a href=\"https://www.unitn.it\">malicious page2</a><br'");
		tester.setTextField("page", "1'><a href=\"https://www.unitn.it\">malicious page</a><br'");
		
		utl.addSubmitButton("//form[@name='editsemester']");
		tester.submit();
		
		tester.assertMatch("Manage Semesters");
		
		tester.assertLinkNotPresentWithText("malicious semesterid");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}
	
	
	@After
	public void cleanUp() {
		tester = utl.LoginAs("schoolmate", "schoolmate");
		tester.assertMatch("Manage Classes");
		
		//tester.setTextField("sitetext", previousValue);
		tester.clickButtonWithText(" Update ");
		
		tester.clickLinkWithExactText("Log Out");
		
	}
		

}