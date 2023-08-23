import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskParser {

    public Task parseTask(String input) {
        if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return  parseEvent(input);
        } else if (input.startsWith("todo")) {
            return  parseTodo(input);
        } else {
            System.out.println("Unknown task type. Please try again.");
        }
        return null;
    }

    private Deadline parseDeadline(String input) {
        Pattern pattern = Pattern.compile("deadline (.+) /by (.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String taskDescription = matcher.group(1);
            String deadlineTime = matcher.group(2);
            return new Deadline(taskDescription, deadlineTime);
        }
        return null;
    }

    private Event parseEvent(String input) {
        Pattern pattern = Pattern.compile("event (.+) /from (.+) /to (.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String eventDescription = matcher.group(1);
            String startTime = matcher.group(2);
            String endTime = matcher.group(3);
            return new Event(eventDescription, startTime, endTime);
        }
        return null;
    }

    private ToDo parseTodo(String input) {
        String taskDescription = input.replace("todo", "").trim();
        return new ToDo(taskDescription);
    }
}
