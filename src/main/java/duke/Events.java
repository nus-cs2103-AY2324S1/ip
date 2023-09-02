package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck
/**
 * Represents an event task
 */
public class Events extends Task {
    protected LocalDate start;
    protected LocalDate end;

    public Events(String description, LocalDate start, LocalDate end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Return the string representation of an event in the right date format
     *
     * @return string with the right date format
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to: " + end.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Return a string representation that can be saved in the file
     *
     * @return a string representation of the right format to save in the file
     */
    public String save() {
        return "E|" + super.status() + "|" + start + "|" + end;
    }
}
