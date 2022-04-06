package cucumber.utils;

import org.apache.log4j.Logger;

public class Log {

    private static Logger Log;

    public static void info(Object oClass, String message) {
        Log = Logger.getLogger(oClass.getClass().getSimpleName());
        Log.info(message);
    }

    public static void warn(Object oClass, String message) {
        Log = Logger.getLogger(oClass.getClass().getSimpleName());
        Log.warn(message);
    }

    public static void error(Object oClass, String message) {
        Log = Logger.getLogger(oClass.getClass().getSimpleName());
        Log.error(message);
    }

    public static void fatal(Object oClass, String message) {
        Log = Logger.getLogger(oClass.getClass().getSimpleName());
        Log.fatal(message);
    }

    public static void debug(Object oClass, String message) {
        Log = Logger.getLogger(oClass.getClass().getSimpleName());
        Log.debug(message);
    }
}
