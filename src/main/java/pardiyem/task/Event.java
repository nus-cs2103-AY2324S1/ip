package pardiyem.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Event extends Task {
    private static final String SPACE = " ";
    private static final String EMPTY_END_ERROR =
            "Whoops, an event need to have a non-empty ending time";
    private static final String EMPTY_START_ERROR =
            "Whoops, an event need to have a non-empty starting time";
    private static final String START_FORMAT_ERROR =
            "Whoops, you forgot to indicate the starting time by using \"/from *insert starting time*\"";
    private static final String END_FORMAT_ERROR =
            "Whoops, you forgot to indicate the ending time by using \"/from *insert ending time*\"";
    private static final int FROM_ARG_PREFIX_LENGTH = 6;
    private static final int TO_ARG_PREFIX_LENGTH = 4;


    protected LocalDate fromDate;
    protected LocalTime fromTime;

    protected LocalDate toDate;
    protected LocalTime toTime;

    public Event(String description, String from, String to, boolean isDone) throws IllegalArgumentException {
        super(description, isDone);
        if (to.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_END_ERROR);
        }
        if (from.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_START_ERROR);
        }
        int ind1 = from.indexOf(SPACE);
        int ind2 = to.indexOf(SPACE);

        try {
            if (ind1 == INVALID_INDEX) {
                this.fromDate = LocalDate.parse(from);
            } else {
                this.fromDate = LocalDate.parse(from.substring(0, ind1));
                this.fromTime = LocalTime.parse(from.substring(ind1 + 1));
            }

            if (ind2 == INVALID_INDEX) {
                this.toDate = LocalDate.parse(to);
            } else {
                this.toDate = LocalDate.parse(to.substring(0, ind2));
                this.toTime = LocalTime.parse(to.substring(ind2 + 1));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(DATETIME_FORMAT_ERROR);
        }
    }

    public Event(String description, String from, String to) throws IllegalArgumentException {
        this(description, from, to, false);
    }

    public Event(String command) {
        this(parseDesc(command).get(0), parseDesc(command).get(1), parseDesc(command).get(2));
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

    private static ArrayList<String> parseDesc(String desc) throws IllegalArgumentException {
        int i = desc.indexOf("/from");
        if (i == -1) {
            throw new IllegalArgumentException(START_FORMAT_ERROR);
        }
        int j = desc.indexOf("/to");
        if (j == -1) {
            throw new IllegalArgumentException(END_FORMAT_ERROR);
        }
        ArrayList<String> out = new ArrayList<String>();
        if (j > i) {
            out.add(i == 0 ? "" : desc.substring(0, i - 1));
            out.add(desc.substring(i + FROM_ARG_PREFIX_LENGTH, j - 1));
            out.add(desc.substring(j + TO_ARG_PREFIX_LENGTH));
        } else {
            out.add(j == 0 ? "" : desc.substring(0, j - 1));
            out.add(desc.substring(i + FROM_ARG_PREFIX_LENGTH));
            out.add(desc.substring(j + TO_ARG_PREFIX_LENGTH, i - 1));
        }
        return out;
    }
}
