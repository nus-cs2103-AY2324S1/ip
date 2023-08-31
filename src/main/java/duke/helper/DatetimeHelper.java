package duke.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 
 * A helper class containing methods to help parse and format string datetimes. 
 */
public class DatetimeHelper {

  public static String inputFormat = "yyyy-MM-dd HHmm";
  public static String exampleDatetime = "2015-09-15 1300";

  /**
   * Returns a LocalDateTime from datetime argument
   *
   * @param datetime the string to convert to LocalDateTime
   * @return a LocalDateTime object
   * @throws DateTimeParseException when datetime is not the correct format
   */
  public static LocalDateTime parse(String datetime) {
    return LocalDateTime.parse(datetime, DateTimeFormatter.ofPattern(inputFormat));
  }

  /**
   * Returns a string representation of the LocalDateTime object for printing.
   *
   * @param datetime the LocalDateTime object to convert to string
   * @return a string representation of datetime to print
   */
  public static String format(LocalDateTime datetime) {
    return datetime.format(DateTimeFormatter.ofPattern("MMM dd yyyy hh:mma"));
  }

  /**
   * Returns a string representation of the LocalDateTime object in its input format.
   *
   * @param datetime the LocalDateTime object to convert to string
   * @return a string representation of datetime similar to the input format
   */
  public static String commandFormat(LocalDateTime datetime) {
    return datetime.format(DateTimeFormatter.ofPattern(inputFormat));
  }
}
