package cyrus.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtility {
  private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
  private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM " +
      "yyyy");

  public static LocalDate parse(String str) {
    try {
      return LocalDate.parse(str, inputFormatter);
    } catch (DateTimeParseException e) {
      return null;
    }
  }

  public static String formatLocalDate(LocalDate ld) {
    return ld.format(outputFormatter);
  }

  public static String toInputFormat(LocalDate ld) {
    return ld.format(inputFormatter);
  }
}
