import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Parser {
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

    public static String getTaskEvent(String input) {
        String task = input.split("/from")[0].trim();
        return task;
    }

    public static String getTaskDeadline(String input) {
        String task = input.split("/by")[0].trim();
        return task;
    }

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

    public static String extractDatetime(String input) {
        String[] parts = input.split("/by", 2);
        if (parts.length > 1) {
            return parts[1].trim();
        }
        return "";
    }

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
