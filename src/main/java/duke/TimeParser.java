package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A parser to parse a String into a LocalDate object.
 */
public class TimeParser {
  /**
   * Creates a new parser to parse String into LocalDate objects.
   */
  public TimeParser() {}

  /**
   * Parse a string into a LocalDate object
   *
   * @param s string to be parsed
   * @return LocalDate
   * @throws InvalidDateException when String is not in dd/MM/yyyy
   */
  public LocalDate parseTime(String s) throws InvalidDateException {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
      return LocalDate.parse(s, formatter);
    } catch (DateTimeParseException e) {
      throw new InvalidDateException();
    }
  }
}
