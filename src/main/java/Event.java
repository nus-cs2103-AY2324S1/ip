import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Event extends Task {
  protected LocalDate from;
  protected LocalDate to;

  public Event(String description, String from, String to) {
      super(description);
      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
      this.from = LocalDate.parse(from, inputFormatter);
      this.to = LocalDate.parse(to, inputFormatter);
  }

  @Override
  public String toString() {
      DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
      return "[E]" + super.toString() + " (from: " + from.format(outputFormatter) + " to: " + to.format(outputFormatter) + ")";
  }
}
