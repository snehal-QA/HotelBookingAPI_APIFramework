package generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class utils {
	
	public static String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		  System.out.println(resp);
		  JsonPath   js = new JsonPath(resp);
		  System.out.println(js.get(key).toString());
		  return js.get(key).toString();
	}
    
	public static String getPropertyValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
		prop.load(fis);
		return prop.getProperty(key);		
	}
	
}
