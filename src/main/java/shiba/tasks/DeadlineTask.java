package shiba.tasks;

import shiba.datetimeformats.DateOptionalTime;
import shiba.exceptions.InvalidCommandException;
import shiba.parsers.SpaceSeparatedValuesParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineTask extends ShibaTask {
    private static final Pattern deadlineRegex = Pattern.compile("(.+?) /by (.+)");

    /**
     * Parses a DeadlineTask from a command.
     *
     * @param cmd The command to be parsed.
     * @return The DeadlineTask parsed from the command, or null if the command is invalid.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static DeadlineTask fromCmd(String cmd) throws InvalidCommandException {
        String[] cmdSplit = cmd.split(" ", 2);
        if (cmdSplit.length != 2) {
            throw new InvalidCommandException("Deadline name should not be empty!");
        }

        Matcher matcher1 = deadlineRegex.matcher(cmdSplit[1]);
        if (matcher1.find()) {
            return new DeadlineTask(matcher1.group(1), matcher1.group(2));
        }

        throw new InvalidCommandException("Invalid deadline format! Deadline name, /by parameter must be present and not empty.");
    }

    private final DateOptionalTime deadline;

    public DeadlineTask(String name, String deadline) throws InvalidCommandException {
        super(name, TaskType.DEADLINE);
        this.deadline = new DateOptionalTime(deadline);
    }

    @Override
    public String toSaveString() {
        return SpaceSeparatedValuesParser.convert("D", isDone ? "1" : "0", name, deadline.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + deadline.getDisplayRepr() + ")";
    }
}
