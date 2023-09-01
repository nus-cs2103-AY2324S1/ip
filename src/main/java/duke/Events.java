package duke;

import duke.DateTime;

public class Events extends Task {

    protected String start;
    protected String end;
    protected DateTime dtStart;
    protected DateTime dtEnd;


    public Events(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
        this.dtStart = new DateTime(start);
        this.dtEnd = new DateTime(end);
    }

    @Override
    public String getSavingFormat() {
        return "[E] | [" + getStatusIcon() + "] | " + description + " | " + dtStart + " | " + dtEnd;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + dtStart + ", to: " + dtEnd + ")";
    }
}
