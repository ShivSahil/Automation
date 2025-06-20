package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ConstantClasses.ConsoleColors;

public class WrappedReportLogger {

	private static final Logger logger = LogManager.getLogger(WrappedReportLogger.class);
	
	// navigation, click etc
	public static void trace(String message) {
		logger.trace(message);
	}

	public static void debug(String message) {
		logger.debug(message);
	}

	// verification/validation pass
	public static void info(String message) {
		logger.info(ConsoleColors.GREEN+message+ConsoleColors.RESET);
	}
	
	// verification fail
	public static void warn(String message) {
		logger.warn(ConsoleColors.YELLOW+message+ConsoleColors.RESET);
	}

	// validation fail
	public static void error(String message) {
		logger.error(ConsoleColors.RED_BOLD+message+ConsoleColors.RESET);

	}
	// fatal error
	public static void fatal(String message) {
		logger.fatal(ConsoleColors.RED_BOLD+message+ConsoleColors.RESET);

	}

}
