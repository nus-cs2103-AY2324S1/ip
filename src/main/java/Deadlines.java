import java.time.LocalDate;

public class Deadlines extends ListItem {

    LocalDate end;

    /**
     * Constructor for Deadlines.
     */
    public Deadlines(String text, String end) {
        super(text);
        this.end = super.parseDate(end);
    }

    /**
     * Returns string representation of Deadlines.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)", this.end.toString());
    }
}
