package pardiyem.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Deadline extends Task {

    protected LocalTime doByTime;
    protected LocalDate doByDate;

    public Deadline(String description, String doBy, boolean isDone) throws IllegalArgumentException {
        super(description, isDone);
        if (doBy.isEmpty()) {
            throw new IllegalArgumentException("Whoops, a deadline needs to have a non-empty do by description");
        }
        int ind = doBy.indexOf(" ");
        try {
            if (ind == -1) {
                this.doByDate = LocalDate.parse(doBy);
            } else {
                this.doByDate = LocalDate.parse(doBy.substring(0, ind));
                this.doByTime = LocalTime.parse(doBy.substring(ind+1));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please input your time in the format of either \"YYYY-MM-DD\" or \"YYYY-MM-DD HH:MM:SS\"");
        }
    }

    public Deadline(String description, String doBy) {
        this(description, doBy, false);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s%s)",
                super.toString(), 
                doByDate.toString(), 
                doByTime != null ? " " + doByTime.toString() : "");
    }

    public static ArrayList<String> parseDesc(String desc) throws IllegalArgumentException {
        int i = desc.indexOf("/by");
        if (i == -1) {
            throw new IllegalArgumentException("Whoops, you forgot to indicate the deadline by using \"/by *insert deadline*\"");
        }
        ArrayList<String> out = new ArrayList<String>();
        out.add(i == 0 ? "" : desc.substring(0, i - 1));
        out.add(desc.substring(i + 4));
        return out;
    }

}
