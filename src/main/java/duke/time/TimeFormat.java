package duke.time;

import duke.Duke;
import duke.exception.DukeException;
import duke.exception.TimeFormatException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Represent the Time of tasks for Deadline and Event.
 * Time has 2 formats, the amPmFormat and the fullDayFormat.
 */
public class TimeFormat {

	public static LocalDateTime fullDayFormat(String description, String item) throws DukeException {
		StringBuilder startTime = new StringBuilder();
		String[] timePhrase = Arrays.copyOfRange(item.split(" "), 1, 3);
		startTime.append(timePhrase[1]).append("T").append(timePhrase[0]);
		try {
			LocalDateTime timeFormat = LocalDateTime.parse(startTime.toString());
			return timeFormat;
		} catch (DateTimeParseException e) {
			throw new DukeException("Format of date and time is not right \n" + e.getMessage());
		}
	}

	public static String getDay(String dayAndMonth, boolean getDay) {
		String day;
		String month;
		try {
			Integer.parseInt(dayAndMonth.substring(1, 2));
			day = dayAndMonth.substring(0,2);
			month = dayAndMonth.substring(2);
		} catch (NumberFormatException e) {
			day = dayAndMonth.substring(0,1);
			month = dayAndMonth.substring(1);
		}
		return getDay ? day : month;
	}

	/**
	 * Returns the Date formatted in the form: 2015-02-20T06:30:00.
	 *
	 * @param timeDateNoFormat Array containing time and date at index 0 and 1.
	 * @return Formatted String.
	 * @throws TimeFormatException For invalid time formats.
	 */
	// this is actually the amPmFormat
	public static LocalDateTime amPmFormat(String[] timeDateNoFormat) throws TimeFormatException {
		String[] timeDate = Arrays.copyOfRange(timeDateNoFormat, 1, timeDateNoFormat.length);
		SimpleDateFormat inputTime = new SimpleDateFormat("ha");
		SimpleDateFormat outputTime = new SimpleDateFormat("HH:mm:ss");
		String year = Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		try {
			Date time = inputTime.parse(timeDate[0]);
			String formatTime = outputTime.format(time);

			String dayAndMonth = timeDate[1];
			String day = TimeFormat.getDay(dayAndMonth, true);
			String month = TimeFormat.getDay(dayAndMonth, false);

			StringBuilder dateFormatBuilder = new StringBuilder();
			dateFormatBuilder.append(year).append("-").append(monthValue(month)).append("-").append(day).append("T").append(formatTime);
			return LocalDateTime.parse(dateFormatBuilder.toString());
		} catch (ParseException | IllegalArgumentException | DateTimeParseException e) {
			throw new TimeFormatException("format of time is not right, enter it as /by 630pm 18june\n" + e.getMessage());
		}
	}
	/**
	 * Returns the integer value of a month.
	 *
	 * @param month String representation of month.
	 * @return Integer representation of month.
	 * @throws IllegalArgumentException Throws exception should the month not be recognised.
	 */
	public static String monthValue(String month) throws IllegalArgumentException {
		switch (month.toLowerCase()) {
			case "january":
				return "01";
			case "february":
				return "02";
			case "march":
				return "03";
			case "april":
				return "04";
			case "may":
				return "05";
			case "june":
				return "06";
			case "july":
				return "07";
			case "august":
				return "08";
			case "september":
				return "09";
			case "october":
				return "10";
			case "november":
				return "11";
			case "december":
				return "12";
			default:
				throw new IllegalArgumentException("Invalid month string");
		}
	}

}
