public class Deadlines extends ListItem {

    String end;

    /**
     * Constructor for Deadlines.
     */
    public Deadlines(String text, String end) {
        super(text);
        this.end = end;
    }

    /**
     * Returns string representation of Deadlines.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + String.format(" (by: %s)", this.end);
    }
}
