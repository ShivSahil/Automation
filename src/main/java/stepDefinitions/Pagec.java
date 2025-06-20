package stepDefinitions;

import customized.WrappedAssert;
import io.cucumber.java.en.When;

public class Pagec {
	
	@When("read the title of page")
	public void  read_the_title_of_page() {
		
//		if((int)(Math.random()*10)<1) {
//			WrappedAssert.assertTrue(false, "failed message");
//		}
//		else {
			WrappedAssert.assertTrue(true, "passed message");
		//}
		
	}

}
