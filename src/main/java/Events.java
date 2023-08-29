import java.util.Scanner;

public class Events extends Task {

    protected String start;
    protected String end;

    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String getSavingFormat() {
        return "[E] | [" + getStatusIcon() + "] | " + description + " | " + start + " | " + end;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + start + " to: " + end + ")";
    }
}
