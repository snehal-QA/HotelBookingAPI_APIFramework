package generic;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class utils {
	
	public static Map<String,String> mapStr = new HashMap<String, String>();
		
	public static String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		  System.out.println(resp);
		  JsonPath   js = new JsonPath(resp);
		  //System.out.println(js.get(key).toString());
		  return js.get(key).toString();
	}
    
	public static String getPropertyValue(String key) throws IOException
	{
		Properties prop =new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"/src/main/java/resources/config.properties");
		prop.load(fis);
		return prop.getProperty(key);		
	}
	
	public static List<String> jsonArray_getkey(String jsonArrayStr, String key) {
	    JSONArray jsonArray = new JSONArray(jsonArrayStr); 
	    return IntStream.range(0, jsonArray.length())
	      .mapToObj(index -> ((JSONObject)jsonArray.get(index)).optString(key))
	      .collect(Collectors.toList());
	}
	
	public static String  handleValue(Object value) {
	    if (value instanceof JSONObject) {
	        handleJSONObject((JSONObject) value);
	    } 
	    else if (value instanceof JSONArray) {
	        handleJSONArray((JSONArray) value);
	    } 
	    return value.toString();
	}
	
	public static void handleJSONObject(JSONObject jsonObject) {
	    jsonObject.keys().
	    forEachRemaining(key -> {
	        Object value = jsonObject.get(key);
	        if(value instanceof JSONObject) 
	        { handleValue(value);} 
	        else 
		    { mapStr.put(key, String.valueOf(value));}
	    });
	}
	
	
	  public static void handleJSONArray(JSONArray jsonArray) {
	  jsonArray.iterator(). forEachRemaining(element -> handleValue(element)); }
	
	
}
