package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deadline extends Task {
    private static final Pattern PATTERN_COMMAND_CREATE_DEADLINE =
            Pattern.compile("^deadline ?(?<taskName>.*?)? ?(/by (?<finishByTime>.*))?$");
    private LocalDate finishByTime;

    Deadline(String name, LocalDate finishByTime) {
        super(name);
        this.finishByTime = finishByTime;
    }
    Deadline(boolean isDone, String name, LocalDate finishByTime) {
        super(name, isDone);
        this.finishByTime = finishByTime;
    }

    /**
     * Creates and returns a Deadline
     *
     * @param command String command of the form 'deadline ... /by ...'
     * @return Deadline object created
     * @throws LukeException If the command is invalid
     */
    public static Deadline createDeadline(String command) throws LukeException {
        Matcher matcher = PATTERN_COMMAND_CREATE_DEADLINE.matcher(command);
        matcher.find();

        String taskName = matcher.group("taskName");
        if (taskName == null || taskName.isBlank()) {
            throw new LukeException("The description of a deadline cannot be empty.");
        }
        String finishByTime = matcher.group("finishByTime");
        if (finishByTime == null || finishByTime.isBlank()) {
            throw new LukeException("The due date (/by ...) of a deadline cannot be empty.");
        }

        try {
            return new Deadline(taskName, LocalDate.parse(finishByTime));
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid date format");
        }
    }

    /**
     * Creates and returns a Deadline
     * This function assumes correct ordering of args provided.
     *
     * @param args Arguments to be used to create the Deadline from its constructor
     * @param isDone Boolean indicating if the Deadline is done
     * @return Deadline object created
     * @throws LukeException If there is insufficient/ excessive number of arguments in args,
     *         or if the argument for date provided is invalid
     */
    public static Deadline createDeadline(String[] args, boolean isDone) throws LukeException {
        if (args.length != 2) {
            throw new LukeException("Error creating Deadline: Incorrect number of arguments");
        }
        try {
            return new Deadline(isDone, args[0], LocalDate.parse(args[1]));
        } catch (DateTimeParseException e) {
            throw new LukeException("Error creating Deadline: Invalid date format");
        }
    }

    @Override
    public String toSaveStr() {
        return "D"
                + " | " + super.toSaveStr()
                + " | " + finishByTime;
    }

    /**
     * Determines if this Deadline is equal to another object
     *
     * @param o Other object to be compared with
     * @return true if o is a Deadline, satisfies the equals condition of its superclass,
     *         and has the same finishByTime.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline deadlineObj = (Deadline) o;

            return super.equals(o) && finishByTime.equals(deadlineObj.finishByTime);
        }

        return false;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + finishByTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)) + ")";
    }
}
