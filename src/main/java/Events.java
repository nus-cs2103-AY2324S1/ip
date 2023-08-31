import java.time.LocalDate;

public class Events extends ListItem {

    LocalDate start;
    LocalDate end;

    /**
     * Constructor for Events.
     */
    public Events(String text, String start, String end) {
        super(text);
        this.start = super.parseDate(start);
        this.end = super.parseDate(end);
    }

    /**
     * Returns string representation of Events.
     */
    @Override
    public String toString() {
        return (
                "[E] " +
                        super.toString() +
                        String.format(" (from: %s to: %s)", this.start.toString(), this.end.toString())
        );
    }
}
