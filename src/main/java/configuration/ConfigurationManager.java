package configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import ConstantClasses.IAutoConstant;

public class ConfigurationManager {
	
	// I wrote this class for accessing Properties file
	
	private static Properties prop;
	
	public static Properties init_prop(){
		
		prop = new Properties();
		try {
			//FileInputStream fis= new FileInputStream("/home/shiv/eclipse-workspaceShowcase/Automation/src/main/resources/Config/Config.properties");
			// Donot use commented code, because InputStream has following things:
			// 1 Works even when packaged in a JAR (like in Maven projects or CI/CD environments).
			// 2. works with both ubuntu and windows
			// 3. have Better portability across environments.
			
			InputStream fis= ConfigurationManager.class.getResourceAsStream(IAutoConstant.CONFIG_PATH);
			prop.load(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
		
	}
	


}
