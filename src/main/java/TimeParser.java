import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeParser {
  public TimeParser() {}

  public LocalDate parseTime(String s) throws InvalidDateException {
    try {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy]");
      return LocalDate.parse(s, formatter);
    } catch (DateTimeParseException e) {
      throw new InvalidDateException();
    }
  }
}
