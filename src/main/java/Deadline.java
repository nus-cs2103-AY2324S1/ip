import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Deadline extends Task {
  private LocalDate deadline = null;

  public Deadline(String description, String by) {
      super(description);
      DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      this.deadline = LocalDate.parse(by, inputFormatter);
  }

  @Override
  public String toString() {
      DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
      return "[D]" + super.toString() + " (by: " + deadline.format(outputFormatter) + ")";
  }
}
