import java.util.Scanner;

public class Deadlines extends Task {

    protected String date;

    public Deadlines(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getSavingFormat() {
        return "[D] | [" + getStatusIcon() + "] | " + description + " | " + date;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + date + ")";
    }
}
