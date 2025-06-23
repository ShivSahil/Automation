package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import Pages.CommonPage;
import logging.WrappedReportLogger;

public class TestUtils {
	
	
	
	// My invention; setting variables in feature file directly************************************************
	
	
	public static String resolveDynamicVariableIfNeeded(String key) {   // validating if variables(var_AccountTypeOnAccountDetails) or simple data is send in feature file
	    if (key != null && key.startsWith("var_") && !key.contains("=@")) {
	        String dynamicVal = CommonPage.getThreadSafeMap().get(key);
	        if (dynamicVal != null) {   // if variable is found then return the value
	            return dynamicVal.toString();
	        } else {
	        	WrappedReportLogger.error("Dynamic variable '" + key + "' not found in ThreadLocal map.");
	            throw new RuntimeException("Dynamic variable '" + key + "' not found in ThreadLocal map.");
	        }
	    }
	    return key;
	}

	
	
	// Method return any String $95,245.50 as 95245.50 in BigDecimal Format
	public static BigDecimal parseToBigDecimal(String amount) {
		try {
		NumberFormat format=NumberFormat.getNumberInstance(Locale.US);
		if(format instanceof DecimalFormat) {
			((DecimalFormat)format).setParseBigDecimal(true);
		}
			return (BigDecimal) format.parse(amount.replaceAll("[^\\d.-]", ""));
		} catch (ParseException e) {
			WrappedReportLogger.error("unable to parse amount '" + amount+ "' to Big Decimal");
			return BigDecimal.ZERO;
		}
	}
	
	// My invention; these @field lets you get data on demand directly on feature file************************************************
	
	// example use in feature file:
	// @randomNumber1till99
	public static int getRandomNumberInRange(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("Min cannot be greater than Max");
		}
		int random = (int) (Math.random() * ((max - min) + 1)) + min;
		WrappedReportLogger.debug("Random Number generated between '" + min + "' and '" + max + "' is '"+ random+"'");
		return random;
	}

	// example use in feature file:
	// @todayDateInddMM @yesterdayDateInMM/dd/YYYY
	public static String getFormattedDate(String keyword, String format) {
		Calendar calendar = Calendar.getInstance();

		switch (keyword.toLowerCase()) {
		case "yesterday":
			calendar.add(Calendar.DAY_OF_MONTH, -1);
			break;
		case "tomorrow":
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			break;
		case "today":
		default:
			// no change needed
			break;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		WrappedReportLogger.debug(keyword+" is returned as '" +sdf.format(calendar.getTime())+"'");
		return sdf.format(calendar.getTime());
	}

	// example use in feature file:
	// @randomDateFrom15/01/2020TillTodayInMMddYYY
	// @randomDateFromYesterdayTill15/01/2029InMMdd
	// @randomDateFrom01/12/2020Till15/01/2029Indd-MM-YYYY
	public static String getFormattedRandomDate(String startDate, String endDate, String outputFormat) {
		try {
			// input format of start and end date is assumed as dd/MM/yyyy  *********
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			startDate = resolveKeyword(startDate, sdf);
			endDate = resolveKeyword(endDate, sdf);

			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);

			if (start.after(end)) {
				throw new IllegalArgumentException("Start date must be before or equal to end date.");
			}

			long randomMillis = start.getTime() + (long) (Math.random() * (end.getTime() - start.getTime()));
			
			// Format the output date to desired output
			String randomDate= new SimpleDateFormat(outputFormat).format(new Date(randomMillis));
			WrappedReportLogger.debug("Random Date generated between '" + startDate + "' and '" + endDate + "' is '"+ randomDate+"'");
			return randomDate;

		} catch (Exception e) {
			WrappedReportLogger.error("Error generating random date: " + e.getMessage());
		}
		return null;
	}

	
	// example use in feature file:
	// @randomFirstName
	public static String generateRandomFirstName() {
		try {
			String[] adjectives = { "Sharp", "Swift", "Mighty", "Bold", "Clever", "Wild", "Brave", "Fierce" };
			String[] animals = { "Tiger", "Falcon", "Panther", "Wolf", "Eagle", "Lion", "Cheetah", "Bear" };
			
			Random random = new Random();
			 
			String adjective = adjectives[random.nextInt(adjectives.length)];
			String animal = animals[random.nextInt(animals.length)];
			int suffix = 100 + random.nextInt(900);

			String name = "TestFname_" + adjective + animal + "_" + suffix;
			WrappedReportLogger.debug("Generated random first name: '" + name + "'");
			return name;
		} catch (Exception e) {
			WrappedReportLogger.error("Unable to generate random first name");
			return null;
		}
	}
	
	// example use in feature file:
	// @randomLastName
	public static String generateRandomLastName() {
		try {
			String[] adjectives = { "Silent", "Bright", "Dark", "Golden", "Hidden", "Crisp", "Lone", "Silver" };
			String[] animals = { "Fox", "Hawk", "Bear", "Wolf", "Stag", "Lynx", "Ram", "Raven" };

			Random random = new Random();
			
			String adjective = adjectives[random.nextInt(adjectives.length)];
			String animal = animals[random.nextInt(animals.length)];
			int suffix = 100 + random.nextInt(900);

			String name = "TestLname_" + adjective + animal + "_" + suffix;
			WrappedReportLogger.debug("Generated random last name: '" + name + "'");
			return name;
		} catch (Exception e) {
			WrappedReportLogger.error("Unable to generate random last name");
			return null;
		}
	}

	
	// example use in feature file:
	// @randomDropDownVaue
	public static String selectRandomDropDownValue() {
		return null;
	}

	private static String resolveKeyword(String keyword, SimpleDateFormat sdf) {
		Calendar cal = Calendar.getInstance();
		switch (keyword.toLowerCase()) {
		case "today":
			return sdf.format(cal.getTime());
		case "yesterday":
			cal.add(Calendar.DAY_OF_YEAR, -1);
			return sdf.format(cal.getTime());
		case "tomorrow":
			cal.add(Calendar.DAY_OF_YEAR, 1);
			return sdf.format(cal.getTime());
		default:
			return keyword;
		}
	}
	
	public static int[] extractRangeFromRandomNumber(String tag) {
	    try {
	        // Assumes format like @randomNumber1till99
	        tag = tag.toLowerCase().replaceAll("[^0-9till]", ""); // removes all except digits and 'till'
	        String[] parts = tag.split("till");

	        if (parts.length != 2) {
	            throw new IllegalArgumentException("Invalid format. Expected format: @randomNumber1till99");
	        }

	        int min = Integer.parseInt(parts[0]);
	        int max = Integer.parseInt(parts[1]);

	        if (min > max) {
	            throw new IllegalArgumentException("Min cannot be greater than Max");
	        }

	        return new int[] { min, max };
	    } catch (Exception e) {
	        throw new RuntimeException("Failed to extract range from tag: " + tag + ". " + e.getMessage());
	    }
	}
	
	public static String[] extractDateAndFormat(String tag) {
	    if (tag == null || !tag.startsWith("@")) {
	        throw new IllegalArgumentException("Invalid tag format: " + tag);
	    }

	    // Find the index of "DateIn" (case-insensitive)
	    int index = tag.toLowerCase().indexOf("datein");
	    if (index == -1) {
	        throw new IllegalArgumentException("Tag must contain 'DateIn': " + tag);
	    }

	    String keyword = tag.substring(1, index); // skip the '@'
	    String format = tag.substring(index + "DateIn".length());

	    if (keyword.isEmpty() || format.isEmpty()) {
	        throw new IllegalArgumentException("Tag must follow format like @todayDateInDDMM");
	    }

	    return new String[]{keyword, format};
	}

	public static String[] extractStartAndEndDatesAndFormat(String tag) {
	    if (tag == null || !tag.startsWith("@randomDateFrom") || !tag.contains("Till") || !tag.contains("In")) {
	        return new String[] {"Invalid", "Invalid", "Invalid"};
	    }

	    try {
	        // Remove the prefix
	        String trimmed = tag.replaceFirst("@randomDateFrom", "");

	        // Split at 'Till' and 'In'
	        String[] tillSplit = trimmed.split("Till");
	        String[] inSplit = tillSplit[1].split("In", 2);

	        String startDate = tillSplit[0].trim();    
	        String endDate = inSplit[0].trim();       
	        String format = inSplit[1].trim();         

	        return new String[] {startDate, endDate, format};
	    } catch (Exception e) {
	        return new String[] {"Error", "Error", "Error: " + e.getMessage()};
	    }
	}

	
	
	//Note: I have not implemented method like dateComparison, findDifferenceBetweenDate, **htmlTable** etc etc. Since I had less time

    // a lot of code written down below is copied from internet since it's easy available online and I was under time crunch!
		


}
