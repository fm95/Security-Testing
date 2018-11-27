package it.unitn.testing;

import utility.*;
import org.junit.*;
import net.sourceforge.jwebunit.junit.*;

public class Template {
	
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
	
		//
		utl.SetPreviousValue("html//textarea[@name='sitetext']");
		//
		
		
	}
	
	@After
	public void cleanUp() {
		utl.clean();
	}

}
