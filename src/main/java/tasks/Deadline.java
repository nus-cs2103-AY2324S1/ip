package tasks;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final String by;
    private LocalDate date;

    /**
     * Constructor for Deadline class for stdin inputs
     * @param description
     */
    public Deadline(String description) {
        super(description.substring(0, description.indexOf(" /by")));
        this.by = description.substring(description.indexOf(" /by ") + 5);
        setDate(this.by);  
    }

    /**
     * Constructor for Deadline class for inputs from file
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        setDate(this.by);  
    }

    /**
     * Setter for date, checks if date is in yyyy-MM-dd format
     * @param by
     */
    private void setDate(String by) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.date = LocalDate.parse(by, formatter);
        } catch (DateTimeParseException e) {
            this.date = null;
        }
    }

    /**
     * Getter for date
     * @return date
     */
    private String getDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), date != null
                    ? getDate(date)
                    : by);
    }
}
