package it.unitn.testing;

import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import org.xml.sax.helpers.AttributesImpl;

import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.InputElementFactory;

import net.sourceforge.jwebunit.api.IElement;
import net.sourceforge.jwebunit.htmlunit.HtmlUnitElementImpl;
import net.sourceforge.jwebunit.junit.WebTester;


public class TestAddAssignment11 {

	private WebTester tester;
	//private String previousValue;

	
	@Before
	public void prepare() {
		tester = new WebTester();
		tester.setBaseUrl("http://localhost/schoolmate");
	}

	@Test
	public void test() {
		tester.beginAt("/index.php");
		tester.setTextField("username", "marior");
		tester.setTextField("password", "teacher");
		tester.submit();
	
		// check if i'm in the right page
		tester.assertMatch("Mario Rossi's Classes");
		tester.clickLinkWithText("SecTest");
		
		tester.assertMatch("Class Settings");
		tester.clickLinkWithText("Assignments");
		
		tester.assertMatch("Manage Assignments");
		tester.setWorkingForm("teacher");
		tester.setTextField("page2", "4'> <a href=\"https://www.unitn.it\">malicious link</a>");
		//tester.clickButtonWithText("Add");
		
		addSubmitButton("//form[@name='teacher']");
		tester.submit();
		
		tester.assertMatch("Add New Assignment");
		// check for injection
		tester.assertLinkNotPresentWithText("malicious link");
		// if the vulnerability is present, the test must fail!
		
	}

	private void addSubmitButton(String xpath) {
		IElement element = tester.getElementByXPath(xpath);
		DomElement form = ((HtmlUnitElementImpl) element).getHtmlElement();
		InputElementFactory factory = InputElementFactory.instance;
		AttributesImpl attributes = new AttributesImpl();
		attributes.addAttribute("","","type","","submit");
		HtmlElement submit = factory.createElement(form.getPage(), "input", attributes);
		form.appendChild(submit);
	}

	@After
	public void cleanUp() {
		tester.beginAt("/index.php");
		tester.setTextField("username", "marior");
		tester.setTextField("password", "teacher");
		tester.submit();
		
		
		
	}


}
