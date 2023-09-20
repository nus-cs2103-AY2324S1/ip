package pardiyem.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Deadline extends Task {

    protected LocalTime doByTime;
    protected LocalDate doByDate;
    private static final String EMPTY_DEADLINE_ERROR =
            "Whoops, a deadline needs to have a non-empty do by description";
    private static final String DEADLINE_FORMAT_ERROR =
            "Whoops, you forgot to indicate the deadline by using \"/by *insert deadline*\"";

    private static final int BY_ARG_PREFIX_LENGTH = 4;

    /**
     * A constructor to the Deadline class. Parses the do-by string argument into date and time format
     *
     * @param description string argument to describe the task
     * @param doBy string argument to indicate the due date, must be entered in the format "YYYY-MM-DD" or "YYYY-MM-DD HH:MM:SS"
     * @param isDone boolean argument to indicate whether the task has been done
     * @throws IllegalArgumentException if any of the argument are empty or invalid
     */
    public Deadline(String description, String doBy, boolean isDone) throws IllegalArgumentException {
        super(description, isDone);
        if (doBy.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_DEADLINE_ERROR);
        }
        int ind = doBy.indexOf(" ");
        try {
            if (ind == INVALID_INDEX) {
                this.doByDate = LocalDate.parse(doBy);
            } else {
                this.doByDate = LocalDate.parse(doBy.substring(0, ind));
                this.doByTime = LocalTime.parse(doBy.substring(ind + 1));
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(DATETIME_FORMAT_ERROR);
        }
    }

    public Deadline(String description, String doBy) {
        this(description, doBy, false);
    }

    public Deadline(String command) {
        this(parseDesc(command).get(0), parseDesc(command).get(1));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s%s)",
                super.toString(),
                doByDate.toString(),
                doByTime != null ? " " + doByTime.toString() : "");
    }

    private static ArrayList<String> parseDesc(String desc) throws IllegalArgumentException {
        int i = desc.indexOf("/by");
        if (i == -1) {
            throw new IllegalArgumentException(DEADLINE_FORMAT_ERROR);
        }
        ArrayList<String> out = new ArrayList<String>();
        out.add(i == 0 ? "" : desc.substring(0, i - 1));
        out.add(desc.substring(i + BY_ARG_PREFIX_LENGTH));
        return out;
    }

}
