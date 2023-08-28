import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class DateArgument extends Argument {

  public DateArgument(String text) {
    super(text);
  }

  @Override
  public Object formatInput(String input) throws DukeException {
    try {
      return LocalDate.parse(input);
    } catch (DateTimeParseException e) {
      throw new InvalidDateException();
    }
  }

  @Override
  public String formatOutput(Object val) {
    return val.toString();
  }
  
}