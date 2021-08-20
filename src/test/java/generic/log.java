package generic;

	
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
	public class log {


	    // Initialize Log4j logs
	    static {
	        String log4jpath=System.getProperty("user.dir")+"/src/test/resources/log4j.xml";
	        System.setProperty("logoutputpath", System.getProperty("user.dir"));
	        System.setProperty("log4j.configurationFile",log4jpath);
	    }

	    private static Logger Log = LogManager.getLogger(Log.class.getName());//

	    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite

	    public static void startTestCase(String sTestCaseName){


	        Log.info("****************************************************************************************");

	        Log.info("$$$$$$$$$$ "+sTestCaseName+" $$$$$$$$$$");

	     


	    }

	    //This is to print log for the ending of the test case

	    public static void endTestCase(String sTestCaseName){


	        Log.info("$$$$$$$$$$ "+sTestCaseName+" $$$$$$$$$$");

	        Log.info("****************************************************************************************");

	    }

	    // Log level methods

	    public static void info(String message) {

	        Log.info("----------" + message + "----------");

	    }

	    public static void warn(String message) {

	        Log.warn("**********" + message + "**********");;

	    }

	    public static void error(String message, Exception e) {

	        Log.error(message,e);

	    }

	    public static void fatal(String message) {

	        Log.fatal("!!!!!!!!!!" + message + "!!!!!!!!!!");

	    }

	    public static void debug(String message) {

	        Log.debug("<<<<<<<<<<" +  message + ">>>>>>>>>>" );

	    }


}


