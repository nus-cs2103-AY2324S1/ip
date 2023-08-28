package TaskPackages;
import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task{

  protected LocalDate byDate;
  protected LocalTime byTime;

  protected Deadline(String description, LocalDate byDate, LocalTime byTime) {
    super(description);
    this.byDate = byDate;
    this.byTime = byTime;
  }
  
  @Override
  public String toString() {
    return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.byDate, this.byTime);
  }
}