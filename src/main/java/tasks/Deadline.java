package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
  private LocalDate by;

  public Deadline(String description, String by) {
    super(description);
    this.symbol = "D";
    try {
      this.by = LocalDate.parse(by);
    } catch (DateTimeParseException e) {
      System.out.println("Invalid date format");
    }
  }

  @Override
  public String save() {
    return String.format("%s|%s|%s", this.symbol, super.save(), this.by);
  }

  @Override
  public String toString() {
    return String.format("[%s]%s (by %s)", this.symbol, super.toString(),
        this.by);
  }
}
