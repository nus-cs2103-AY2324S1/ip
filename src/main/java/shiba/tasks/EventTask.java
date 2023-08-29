package shiba.tasks;

import shiba.datetimeformats.DateOptionalTime;
import shiba.exceptions.InvalidCommandException;
import shiba.parsers.SpaceSeparatedValuesParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventTask extends ShibaTask {
    private static final Pattern option1Regex = Pattern.compile("(.+?) /from (.+?) /to (.+)");
    private static final Pattern option2Regex = Pattern.compile("(.+?) /to (.+?) /from (.+)");

    /**
     * Parses a EventTask from a command.
     *
     * @param cmd The command to be parsed.
     * @return The EventTask parsed from the command, or null if the command is invalid.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static EventTask fromCmd(String cmd) throws InvalidCommandException {
        String[] cmdSplit = cmd.split(" ", 2);
        if (cmdSplit.length != 2) {
            throw new InvalidCommandException("Event name should not be empty!");
        }

        Matcher matcher1 = option1Regex.matcher(cmdSplit[1]);
        if (matcher1.find()) {
            return new EventTask(matcher1.group(1), matcher1.group(2), matcher1.group(3));
        }

        Matcher matcher2 = option2Regex.matcher(cmdSplit[1]);
        if (matcher2.find()) {
            return new EventTask(matcher2.group(1), matcher2.group(3), matcher2.group(2));
        }

        throw new InvalidCommandException("Invalid event format! Event name, /from and"
                + " /to parameters must be present and not empty.");
    }

    private final DateOptionalTime startTime;
    private final DateOptionalTime endTime;

    public EventTask(String name, String from, String to) throws InvalidCommandException {
        super(name, TaskType.EVENT);
        startTime = new DateOptionalTime(from);
        endTime = new DateOptionalTime(to);
    }

    @Override
    public String toSaveString() {
        return SpaceSeparatedValuesParser.convert("E", isDone ? "1" : "0", name,
                startTime.toString(), endTime.toString());
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + startTime.getDisplayRepr() + " to: "
                + endTime.getDisplayRepr() + ")";
    }
}
