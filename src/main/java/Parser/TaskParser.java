package Parser;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskParser {

    public Task parseTask(String input) {
        try {
            if (input.startsWith("deadline")) {
                return parseDeadline(input);
            } else if (input.startsWith("event")) {
                return parseEvent(input);
            } else if (input.startsWith("todo")) {
                return parseTodo(input);
            } else {
                throw new InvalidTaskFormatException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidTaskFormatException e) {
            System.out.println("☹ OOPS!!! " + e.getMessage());
        }
        return null;
    }

    public Task parseAddTaskCommand(String input) {
        try {
            if (input.startsWith("deadline")) {
                return parseDeadline(input);
            } else if (input.startsWith("event")) {
                return parseEvent(input);
            } else if (input.startsWith("todo")) {
                return parseTodo(input);
            } else {
                throw new InvalidTaskFormatException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidTaskFormatException e) {
            System.out.println("☹ OOPS!!! " + e.getMessage());
        }
        return null;
    }

    private Deadline parseDeadline(String input) throws InvalidTaskFormatException {
        Pattern pattern = Pattern.compile("deadline (.+) by: (.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String taskDescription = matcher.group(1);
            String deadlineTime = matcher.group(2);

            if (taskDescription.isEmpty()) {
                throw new InvalidTaskFormatException("The description of a deadline cannot be empty.");
            }

            return new Deadline(taskDescription, deadlineTime);
        } else {
            throw new InvalidTaskFormatException("Invalid deadline format.");
        }
    }

    private Event parseEvent(String input) throws InvalidTaskFormatException {
        Pattern pattern = Pattern.compile("event (.+) from (.+) to (.+)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String eventDescription = matcher.group(1);
            String startTime = matcher.group(2);
            String endTime = matcher.group(3);

            if (eventDescription.isEmpty()) {
                throw new InvalidTaskFormatException("The description of an event cannot be empty.");
            }

            return new Event(eventDescription, startTime, endTime);
        } else {
            throw new InvalidTaskFormatException("Invalid event format.");
        }
    }

    private ToDo parseTodo(String input) throws InvalidTaskFormatException {
        String taskDescription = input.replace("todo", "").trim();

        if (taskDescription.isEmpty()) {
            throw new InvalidTaskFormatException("The description of a todo cannot be empty.");
        }

        return new ToDo(taskDescription);
    }
}

