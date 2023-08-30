package cyrus.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Utility to deal with {@code LocalDate}.
 */
public class DateUtility {
  private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM " +
      "yyyy");

  /**
   * Parse a string into a {@code LocalDate} of format {@code dd/MM/yyyy}.
   * @param str string to parse
   * @return {@code LocalDate} if the string is of valid format, else {@code null}
   */
  public static LocalDate parse(String str) {
    try {
      return LocalDate.parse(str, inputFormatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  /**
   * Formats {@code LocalDate} to output format of {@code dd MMMM yyyy} such as {@code 15
   * September 2024}.
   * @param ld {@code LocalDate} to format
   * @return date formatted in {@code dd MMMM yyyy}
   */
  public static String formatLocalDate(LocalDate ld) {
    return ld.format(outputFormatter);
  }

  /**
   * Formats {@code LocalDate} to output format of {@code dd/MM/yyyy} such as {@code 15/09/2024}.
   * @param ld {@code LocalDate} to format
   * @return date formatted in {@code dd/MM/yyyy}
   */
  public static String toInputFormat(LocalDate ld) {
    return ld.format(inputFormatter);
  }
}
