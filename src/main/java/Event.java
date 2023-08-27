import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
  private LocalDate from;
  private LocalDate to;

  public Event(String descriptor, String from, String to) {
    super(descriptor);
    this.symbol = "E";
    try {
      this.from = LocalDate.parse(from);
      this.to = LocalDate.parse(to);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date format");
    }
  }

  @Override
  public String save() {
    return String.format("%s|%s|%s|%s", this.symbol, super.save(), this.from,
        this.to);
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (%s to %s)", this.symbol, super.toString(),
        this.from, this.to);
  }
}
