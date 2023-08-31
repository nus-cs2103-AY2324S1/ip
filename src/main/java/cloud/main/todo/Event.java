package cloud.main.todo;



/**
 * Represents an event TODO, which has both a starting and ending timestamp.
 */
public class Event extends Deadline {
    private String timestampStart;

    public Event(String _description, String start, String end) {
        super(_description, end);

        this.timestampStart = start;
    }

    @Override
    public String toString(int number) {
        return String.format(
            "%s | %s#%d: %s | FROM %s | TO %s",
            this.getCompletionString(),
            this.getTypeString(),
            number,
            this.getDescription(),
            this.getStart(),
            this.getEnd()
        );
    }

    @Override
    public String getTypeString() {
        return "E";
    }

    public String getStart() {
        return this.timestampStart;
    }
}
