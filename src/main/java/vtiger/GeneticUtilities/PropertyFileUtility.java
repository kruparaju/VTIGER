package vtiger.GeneticUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Description-This class consists of generic methods to read data from property file
 * @author Krupa
 *
 */

public class PropertyFileUtility {
	/**
	 * This method will read data fron property File
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String readDataFromPropertyFile(String key) throws IOException {
		
		FileInputStream fisp = new FileInputStream(IConstantUtility.PropertyFilePath);
		Properties pObj = new Properties();
		pObj.load(fisp);
	    String value = pObj.getProperty(key);
	    return value;
	}
	
}
