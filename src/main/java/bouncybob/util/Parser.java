package bouncybob.util;

import bouncybob.BouncyBob;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for parsing various types of user input.
 */
public class Parser {

    /**
     * Returns the type of task based on the given string.
     *
     * @param taskType The string representing the task type.
     * @return The TaskType enum value.
     */
    public static BouncyBob.TaskType getTaskType(String taskType) {
        switch (taskType.toLowerCase()) {
            case "todo":
                return BouncyBob.TaskType.TODO;
            case "deadline":
                return BouncyBob.TaskType.DEADLINE;
            case "event":
                return BouncyBob.TaskType.EVENT;
            default:
                return BouncyBob.TaskType.UNKNOWN;
        }
    }

    /**
     * Returns the type of action based on the given string.
     *
     * @param action The string representing the action.
     * @return The Action enum value.
     */
    public static BouncyBob.Action getAction(String action) {
        switch (action.toLowerCase()) {
            case "mark":
                return BouncyBob.Action.MARK;
            case "unmark":
                return BouncyBob.Action.UNMARK;
            case "delete":
                return BouncyBob.Action.DELETE;
            default:
                return BouncyBob.Action.UNKNOWN;
        }
    }

    /**
     * Extracts the task name for an event task from the given input string.
     *
     * @param input The input string containing the task name and date.
     * @return The task name.
     */
    public static String getTaskEvent(String input) {
        String task = input.split("/from")[0].trim();
        return task;
    }

    /**
     * Extracts the task name for a deadline task from the given input string.
     *
     * @param input The input string containing the task name and date.
     * @return The task name.
     */
    public static String getTaskDeadline(String input) {
        String task = input.split("/by")[0].trim();
        return task;
    }

    /**
     * Removes the action keyword from the array and returns the remaining as a combined string.
     *
     * @param arr The array containing the action keyword and other strings.
     * @return The combined string.
     */
    public static String removeAction(String[] arr) {
        String combinedString = "";
        for (int i = 1; i < arr.length; i++) {
            combinedString = combinedString + arr[i];
            if (i != arr.length - 1) {
                combinedString += " ";
            }
        }
        return combinedString;
    }

    /**
     * Extracts the datetime for a deadline task from the given input string.
     *
     * @param input The input string containing the task name and date.
     * @return The datetime string.
     */
    public static String extractDatetime(String input) {
        String[] parts = input.split("/by", 2);
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return "";
    }

    /**
     * Extracts the 'from' and 'to' datetimes for an event task from the given input string.
     *
     * @param string The input string containing the task name and date.
     * @return An array containing the 'from' and 'to' datetimes.
     */
    public static String[] extractFromTo(String string) {
        Pattern pattern = Pattern.compile("/from\\s+(.*?)\\s+/to\\s+(.*)");
        Matcher matcher = pattern.matcher(string);
        String[] fromTo = new String[2];
        if (matcher.find()) {
            fromTo[0] = matcher.group(1).trim();
            fromTo[1] = matcher.group(2).trim();
        }
        return fromTo;
    }
}
