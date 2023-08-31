package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatetimeHelper {

	public static String inputFormat = "yyyy-MM-dd HHmm";
	public static String exampleDatetime = "2015-09-15 1300";

	public static LocalDateTime parse(String datetime) {
		return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(inputFormat));
	}

	public static String format(LocalDateTime datetime) {
		return datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
	}

	public static String commandFormat(LocalDateTime datetime) {
		return datetime.format(DateTimeFormatter.ofPattern(inputFormat));
	}
}
