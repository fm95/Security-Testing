package editVulnerabilities;

import org.junit.*;

import com.gargoylesoftware.htmlunit.html.DomElement;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestEditGrade76 {

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
		
		tester.clickLinkWithText("Grades");
		tester.assertMatch("Grades");
		
		tester.setWorkingForm("grades");
	
		IElement element = tester.getElementByXPath("//select[@name='assignment']");
		DomElement select = ((HtmlUnitElementImpl) element).getHtmlElement();
		select.setTextContent("1--'--><a href=\"https://www.unitn.it\">malicious assignments</a><br'");

		tester.checkCheckbox("delete[]", "1");
		tester.setTextField("delete[]", "1'-- > <a href=\"https://www.unitn.it\">malicious delete</a> <br'");
		tester.setHiddenField("page2", "7'> <a href=\"https://www.unitn.it\">malicious page2</a> <br'");
		tester.setHiddenField("selectclass", "1'> <a href=\"https://www.unitn.it\">malicious selectclass</a> <br'");
		tester.setHiddenField("page", "2'> <a href=\"https://www.unitn.it\">malicious page</a> <br'");
		
		utl.addSubmitButton("//form[@name='grades']");
		tester.submit();
		tester.assertMatch("Edit Grade");

		tester.assertLinkNotPresentWithText("malicious assignments");
		tester.assertLinkNotPresentWithText("malicious delete");
		tester.assertLinkNotPresentWithText("malicious page2");
		tester.assertLinkNotPresentWithText("malicious selectclass");
		tester.assertLinkNotPresentWithText("malicious page");
		
		tester.clickLinkWithText("Log Out");
		tester.assertMatch("Today's Message");
		
	}

}

