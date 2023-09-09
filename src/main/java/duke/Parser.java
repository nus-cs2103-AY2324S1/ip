package duke;

import static java.lang.Integer.parseInt;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Processes the string the user put in.
 */
public class Parser {
    static final char TODO_CHAR = 't';
    static final char DEADLINE_CHAR = 'd';
    static final char EVENT_CHAR = 'e';
    /**
     * Returns the type of task.
     * @param response the string the user put in.
     * @return type of task.
     */
    public static String parseCommandType(String response) throws DukeException {
        if (Objects.equals(response, "bye")) {
            return "bye";
        } else if (Objects.equals(response, "list")) {
            return "list";
        } else if (response.startsWith("delete")) {
            return "delete";
        } else if (response.startsWith("mark")) {
            return "mark";
        } else if (response.startsWith("todo") || response.startsWith("deadline") || response.startsWith("event")) {
            return "task";
        } else if (response.startsWith("find")) {
            return "find";
        } else {
            return "invalid";
        }
    }
    /**
     * Returns the number associated with a delete or mark command.
     * number is reduced by 1 since user will write with 1-indexing while
     * taskList is 0-indexed.
     * Not to be used for other types of commands.
     * @param response the string the user put in.
     * @return task number.
     */
    public static int getTaskNumber(String response) {
        String[] array = response.split(" ");
        assert array.length > 1 : "taskNumber used on array without split.";
        String lastVal = array[array.length - 1];
        return parseInt(lastVal) - 1;
    }

    /**
     * Creates and returns the task given in a command.
     * @param response The string the user put in.
     * @return The task.
     * @throws DukeException if incomplete task or not a task.
     */
    public static Task createNewTask(String response) throws DukeException {
        String[] array = response.split(" ");
        if (response.startsWith("todo")) {
            String title = parseInfo(array, TODO_CHAR)[0];
            return new Todo(title);
        } else if (response.startsWith("deadline")) {
            String[] receivedInfo = parseInfo(array, DEADLINE_CHAR);
            String title = receivedInfo[0];
            String deadline = receivedInfo[1];
            if (!title.isEmpty()) {
                return new Deadline(title, parseTime(deadline));
            } else {
                throw new DukeException("☹ OOPS!!! The title cannot be empty.");
            }
        } else if (response.startsWith("event")) {
            String[] receivedInfo = parseInfo(array, EVENT_CHAR);
            String title = receivedInfo[0];
            String from = receivedInfo[1];
            String to = receivedInfo[2];
            if (!title.isEmpty()) {
                return new Event(title, parseTime(from), parseTime(to));
            } else {
                throw new DukeException("☹ OOPS!!! The title cannot be empty.");
            }
        }
        return null;
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
    private static String[] parseInfo(String[] responseArray, char taskType) throws DukeException {
        String mode = "title";
        StringBuilder title = new StringBuilder();
        StringBuilder deadline = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        for (String command : responseArray) {
            if (Objects.equals(command, "todo") && taskType == TODO_CHAR) {
                continue;
            }
            if (Objects.equals(command, "deadline") && taskType == DEADLINE_CHAR) {
                continue;
            }
            if (Objects.equals(command, "event") && taskType == EVENT_CHAR) {
                continue;
            }
            if (Objects.equals(command, "/by") && taskType == DEADLINE_CHAR) {
                mode = "deadline";
                continue;
            }
            if (Objects.equals(command, "/from") && taskType == DEADLINE_CHAR) {
                mode = "from";
                continue;
            }
            if (Objects.equals(command, "/to") && taskType == DEADLINE_CHAR) {
                mode = "to";
                continue;
            }
            switch (mode) {
            case "title" -> addToString(title, command);
            case "deadline" -> addToString(deadline, command);
            case "from" -> addToString(from, command);
            case "to" -> addToString(to, command);
            default -> throw new DukeException("Invalid mode.");
            }
        }

        if (taskType == TODO_CHAR) {
            return new String[] {title.toString()};
        } else if (taskType == DEADLINE_CHAR) {
            return new String[] {title.toString(), deadline.toString()};
        } else {
            return new String[] {title.toString(), deadline.toString(), to.toString()};
        }
    }

    private static void addToString(StringBuilder original, String stringToAdd) {
        if (!original.isEmpty()) {
            original.append(" ");
        }
        original.append(stringToAdd);
    }

    private static String parseTime(String potentialTime) {
        try {
            LocalDate fromDate = LocalDate.parse(potentialTime);
            return fromDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeException e) {
            return potentialTime;
        }
    }

    /**
     * Used to parse the keyword used in the find command.
     * @param response Command the user put in.
     * @return The keyword user is searching for.
     */
    public static String findKeyword(String response) {
        assert response.startsWith("find") : "findKeyword used on command that does not start with find";
        return response.substring(5);
    }
}
