package base;

import org.apache.logging.log4j.*;

public class Logger {

    public static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    
    public static void info(Class<?> clazz, String message, Object... args) {
        logger.info(clazz.getSimpleName(), message, args);
    }
    public static void warn(Class<?> clazz, String message, Throwable throwable) {
        logger.warn(clazz.getSimpleName(), message, throwable);
    }
    
    public static void error(Class<?> clazz, String message, Throwable throwable) {
        logger.error(clazz.getSimpleName(), message, throwable);
    }

    public static void debug(Class<?> clazz, String message) {
        logger.debug(clazz.getSimpleName(), message);
    }

    public static void trace(Class<?> clazz, String message) {
        logger.trace(clazz.getSimpleName(), message);
    }

    public static void fatal(Class<?> clazz, String message, Throwable throwable) {
        logger.fatal(clazz.getSimpleName(), message, throwable);
    }

}
