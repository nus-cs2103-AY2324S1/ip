import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime byDateTime;
    /**
     * Constructor for Deadline.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("'by 'yyyy-MM-dd HHmm");
        byDateTime = LocalDateTime.parse(by, inputFormatter);

    }

    @Override
    public String formatFile() {
        return "D" + " | " + (isDone ? "1" : "0") + " | " + description + " "
                + byDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));



    }
    /**
     * Overrides the toString() method in Task.
     * @return the string
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + byDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy ha")) + ")";
    }
}