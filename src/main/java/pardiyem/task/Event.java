package pardiyem.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Event extends Task {

    protected LocalDate fromDate;
    protected LocalTime fromTime;

    protected LocalDate toDate;
    protected LocalTime toTime;

    public Event(String description, String from, String to, boolean isDone) throws IllegalArgumentException  {
        super(description, isDone);
        if (to.isEmpty()) {
            throw new IllegalArgumentException("Whoops, an event need to have a non-empty ending time");
        }
        if (from.isEmpty()) {
            throw new IllegalArgumentException("Whoops, an event need to have a non-empty starting time");
        }
        
        int ind1 = from.indexOf(" ");
        int ind2 = to.indexOf(" ");

        try {
            if (ind1 == -1) {
                this.fromDate = LocalDate.parse(from);
            } else {
                this.fromDate = LocalDate.parse(from.substring(0, ind1));
                this.fromTime = LocalTime.parse(from.substring(ind1 + 1));
            }

            if (ind2 == -1) {
                this.toDate = LocalDate.parse(to);
            } else {
                this.toDate = LocalDate.parse(to.substring(0, ind2));
                this.toTime = LocalTime.parse(to.substring(ind2 + 1));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Please input your time in the format of either \"YYYY-MM-DD\" or \"YYYY-MM-DD HH:MM:SS\"");
        }
    }

    public Event(String description, String from, String to) throws IllegalArgumentException {
        this(description, from, to, false);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s%s to: %s%s)", 
                super.toString(), 
                fromDate.toString(),
                fromTime != null ? " " + fromTime.toString() : "", 
                toDate.toString(),
                toTime != null ? " " + toTime.toString() : "");
    }

    public static ArrayList<String> parseDesc(String desc) throws IllegalArgumentException {
        int i = desc.indexOf("/from");
        if (i == -1) {
            throw new IllegalArgumentException("Whoops, you forgot to indicate the starting time by using \"/from *insert starting time*\"");
        }
        int j = desc.indexOf("/to");
        if (j == -1) {
            throw new IllegalArgumentException("Whoops, you forgot to indicate the ending time by using \"/to *insert ending time*\"");
        }
        ArrayList<String> out = new ArrayList<String>();
        if (j > i) {
            out.add(i == 0 ? "" : desc.substring(0, i - 1));
            out.add(desc.substring(i + 6, j - 1));
            out.add(desc.substring(j + 4));
        } else {
            out.add(j == 0 ? "" : desc.substring(0, j - 1));
            out.add(desc.substring(i + 6));
            out.add(desc.substring(j + 4, i - 1));
        }
        return out;
    }
}
