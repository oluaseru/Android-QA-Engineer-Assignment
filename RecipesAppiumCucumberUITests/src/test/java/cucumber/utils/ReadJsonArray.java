package cucumber.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadJsonArray {
    static String deviceConfig;
    static String deviceName;
    static String UDID;
    static String platformVersion;
    static String wdaLocalPort;

    static String appFilePath;
    static String newCommandTimeout;
    static String platformName;
    static String useNewWDA;
    static String showXcodeLog;
    static String automationName;
    static String nativeWebTap;
    static String retryBackoffTime;

    public static String userEmail;
    static String password;

    //@SuppressWarnings("unchecked")
    public void readJson(String device, String pathToJson) {
        deviceConfig = device;
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader content = new FileReader(pathToJson)) {
            //Read JSON file
            Object obj = jsonParser.parse(content);

            JSONArray array = new JSONArray();

            array.add(obj);
            //System.out.println(array);

            //Iterate over appium_capabilities array
            // array.forEach( emp -> parseCapabilitiesObject( (JSONObject) emp ) );

            for (Object num : array) {
                parseCapabilitiesObject((JSONObject) num);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            Log.error(this, deviceConfig + " the device name is NOT listed in the appium_capabilities.json file; ensure the device name is entered exactly as displayed in the json file");
        }
    }

    private static void parseCapabilitiesObject(JSONObject caps) {
        //Get capabilities object within list
        JSONObject capabilitiesObject = (JSONObject) caps.get(deviceConfig);

        //Get deviceName
        deviceName = (String) capabilitiesObject.get("deviceName");
        //System.out.println(deviceName);

        //Get platformVersion
        platformVersion = (String) capabilitiesObject.get("platformVersion");
        //System.out.println(platformVersion);


        capabilitiesObject = (JSONObject) caps.get(AppiumDriverBase.os);

        //Get APP_FILE_PATH
        appFilePath = (String) capabilitiesObject.get("appFilePath");
        //System.out.println(APP_FILE_PATH);

        //Get platformName
        platformName = (String) capabilitiesObject.get("platformName");
        //System.out.println(platformName);

        //Get newCommandTimeout
        newCommandTimeout = (String) capabilitiesObject.get("newCommandTimeout");
        //System.out.println(newCommandTimeout);

        //Get showXcodeLog
        showXcodeLog = (String) capabilitiesObject.get("showXcodeLog");
        //System.out.println(showXcodeLog);

        //Get automationName
        automationName = (String) capabilitiesObject.get("automationName");
        //System.out.println(automationName);
    }
}
