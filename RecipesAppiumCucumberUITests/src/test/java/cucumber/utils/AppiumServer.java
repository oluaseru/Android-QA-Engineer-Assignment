package cucumber.utils;


import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

import java.io.File;
import java.io.IOException;

public class AppiumServer {

    /**
     * AppiumServer class is responsible for creating server sessions which Appium uses to communicate Simulator or actual device via pass commands to device.
     * In this class, the method StartAppiumServer() requires two paths to work:
     * path to NODE_JS, which is located in bin folder once the node has been installed on to the machine,
     * path to NODE_APPIUM, which will be located under the lib folder once Appium has been installed via npm.
     */
    private static AppiumDriverLocalService service;
    static String PATH_TO_NODE_JS = "";
    static String PATH_TO_NODE_APPIUM = "";
    public static int APPIUM_SERVER_PORT;
    public static String APPIUM_SERVER_ADDRESS = "127.0.0.1";


    public String osName = System.getProperty("os.name").toLowerCase();


    public void startAppiumServer() throws Exception {
        //cCheck what operating system (android or iOS) to determine the path to node and appium
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("mac")) {

            PATH_TO_NODE_JS = "/usr/local/bin/node";
            //PATH_TO_NODE_JS = "/usr/local/Cellar/node/12.11.1/bin/node";

            //ensure you install appium into a folder called AppiumServer
            PATH_TO_NODE_APPIUM = System.getProperty("user.home") + "/node_modules/appium/build/lib/main.js";
        } else if (osName.contains("windows")) {
            PATH_TO_NODE_JS = "C:/node.exe";
            PATH_TO_NODE_APPIUM = "C:/node_modules/appium/build/lib/main.js"; //use main.js instead of appium.js
        }

        //Build the Appium service
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .withIPAddress(APPIUM_SERVER_ADDRESS)
                .usingPort(APPIUM_SERVER_PORT).usingDriverExecutable(new File(PATH_TO_NODE_JS))
                .withAppiumJS(new File(PATH_TO_NODE_APPIUM))
                //do not display the session log in console
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                //error, debug this will help to see the trace in appium server
                .withArgument(GeneralServerFlag.LOG_LEVEL, new PropertyReader().readProperty("LOG_LEVEL"))); //debug //info //error
        service.start();
        System.out.println("__________Appium Server has been started!!____________");
        Log.info(this, "Appium Service started at " + APPIUM_SERVER_ADDRESS + " " + APPIUM_SERVER_PORT);
    }

    public void stopAppiumServer() throws Exception {
        if (service != null) {
            service.stop();
            System.out.println("___________Appium Server has been stopped!!__________");
        }
    }


    public void startAppiumServerCmd(String port) {
        APPIUM_SERVER_PORT = Integer.parseInt(port);

        //PATH_TO_NODE_JS = "/usr/local/bin/node";
        //PATH_TO_NODE_JS = "/usr/local/Cellar/node/version/bin/node";
        PATH_TO_NODE_JS = System.getProperty("user.home") + "/appiumServer/node";

        //ensure you install appium into a folder called AppiumServer
        PATH_TO_NODE_APPIUM = System.getProperty("user.home") + "/node_modules/appium/build/lib/main.js";

        CommandLine cmd = new CommandLine(PATH_TO_NODE_JS);
        cmd.addArgument(PATH_TO_NODE_APPIUM);
        cmd.addArgument("--address");
        cmd.addArgument(APPIUM_SERVER_ADDRESS);
        cmd.addArgument("--port");
        cmd.addArgument(port);
        cmd.addArgument("--log-level");
        cmd.addArgument(new PropertyReader().readProperty("LOG_LEVEL"));
        cmd.addArgument("--session-override");
        DefaultExecuteResultHandler handler = new DefaultExecuteResultHandler();
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(1);
        try {
            executor.execute(cmd, handler);
            System.out.println("__________Appium Server has been started!!____________");
            Log.info(this, "Appium Service started at " + APPIUM_SERVER_ADDRESS + " " + APPIUM_SERVER_PORT);
            Thread.sleep(2000);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            Log.info(this, "Appium Service did NOT start");
        }
    }

    public void stopAppiumServerCmd(String port) {
        String PORT = Integer.toString(APPIUM_SERVER_PORT);

        String[] cmd = new String[]{"sh", "-c", "lsof -nti:" + port + " | xargs kill -9"};
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(cmd);
            System.out.println("___________Appium Server has been stopped!!__________");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
