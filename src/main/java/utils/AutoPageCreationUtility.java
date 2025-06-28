package utils;

import org.openqa.selenium.WebDriver;

public class AutoPageCreationUtility {

	// My innovation  **********************8
	// this utility first looks for all the elements on the page
	// then segregates the elements based on the fact if those elements are dropdown, input field, checkbox and button/links.
	// then it will first look for id, data-el-id of the elements if it's unable to find those attributes then it will create the xpath based on text
	// then page class code is generated
	
	// In our framework the original code is much more complex. 
	// where page class plus **** feature file and step def classes**** are also generated and one method is created for each section etc etc
	// below code just just outlines few of the concepts.
	
	
	public static void pageGenerationUtility(WebDriver driver){  // https://seleniumbase.io/demo_page
		
	}
	

}
