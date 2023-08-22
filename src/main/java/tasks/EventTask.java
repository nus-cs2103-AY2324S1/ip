package tasks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventTask extends ShibaTask {
    private static final Pattern option1Regex = Pattern.compile("(.+?) /from (.+?) /to (.+)");
    private static final Pattern option2Regex = Pattern.compile("(.+?) /to (.+?) /from (.+)");

    /**
     * Parses a EventTask from a command.
     * @param cmd The command to be parsed.
     * @return The EventTask parsed from the command, or null if the command is invalid.
     */
    public static EventTask fromCmd(String cmd) {
        String[] cmdSplit = cmd.split(" ", 2);
        if (cmdSplit.length != 2) {
            return null;
        }

        Matcher matcher1 = option1Regex.matcher(cmdSplit[1]);
        if (matcher1.find()) {
            return new EventTask(matcher1.group(1), matcher1.group(2), matcher1.group(3));
        }

        Matcher matcher2 = option2Regex.matcher(cmdSplit[1]);
        if (matcher2.find()) {
            return new EventTask(matcher2.group(1), matcher2.group(3), matcher2.group(2));
        }

        return null;
    }

    private final String startTime;
    private final String endTime;

    public EventTask(String name, String from, String to) {
        super(name);
        startTime = from;
        endTime = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }
}
