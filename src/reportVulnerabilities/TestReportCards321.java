package reportVulnerabilities;

import org.junit.*;
import net.sourceforge.jwebunit.junit.WebTester;
import utility.Utilities;

public class TestReportCards321 {

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
		
		tester.setWorkingForm("classes");
		tester.setHiddenField("page2", "1337");
		
		utl.addSubmitButton("//form[@name='classes']");
		tester.submit();
		
	}

}

