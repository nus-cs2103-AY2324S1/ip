package duke;

import duke.DateTime;

public class Deadlines extends Task {

    protected String date;
    protected DateTime dt;

    public Deadlines(String description, String date) {
        super(description);
        this.date = date;
        this.dt = new DateTime(date);
    }

    @Override
    public String getSavingFormat() {
        return "[D] | [" + getStatusIcon() + "] | " + description + " | " + dt;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dt + ")";
    }
}
