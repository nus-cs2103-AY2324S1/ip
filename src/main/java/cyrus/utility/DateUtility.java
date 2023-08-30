package cyrus.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtility {
  private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("dd MMMM " +
      "yyyy");

  public static LocalDate parse(String str) {
    try {
      return LocalDate.parse(str, INPUT_FORMATTER);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  public static String formatLocalDate(LocalDate ld) {
    return ld.format(OUTPUT_FORMATTER);
  }

  public static String toInputFormat(LocalDate ld) {
    return ld.format(INPUT_FORMATTER);
  }
}
