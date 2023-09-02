package duke;

import java.time.format.DateTimeParseException;

/**
 * Parse text input from the user and feeds it to the UI
 */
public class Parser {

    enum ParserOutput {DELETE, MARK, UNMARK, APPEND, LIST, ECHO, EXIT, FIND}
    enum TaskType {TODO, EVENT, DEADLINE, GENERIC}

    /**
     * Parse the initial input by the user to determine future action
     *
     * @param input the text typed by the user
     * @return An enum which indicates what the UI should do next
     */
    public static ParserOutput parseInput(String input) {
        if (input.startsWith("mark")) {
            return ParserOutput.MARK;
        }
        if (input.startsWith("unmark")) {
            return ParserOutput.UNMARK;
        }
        if (input.startsWith("delete")) {
            return ParserOutput.DELETE;
        }
        if (input.startsWith("list")) {
            return ParserOutput.LIST;
        }
        if (input.startsWith("echo")) {
            return ParserOutput.ECHO;
        }
        if (input.startsWith("bye")) {
            return ParserOutput.EXIT;
        }
        if (input.startsWith("find")) {
            return ParserOutput.FIND;
        }
        return ParserOutput.APPEND;
    }

    /**
     * Determine the type of the task that is to be created
     *
     * @param task the text typed by the user
     * @return An enum which indicates the type of the task to create
     */
    public static TaskType parseTask(String task) {
        if (task.startsWith("todo")) {
            return TaskType.TODO;
        }
        if (task.startsWith("event")) {
            return TaskType.EVENT;
        }
        if (task.startsWith("deadline")) {
            return TaskType.DEADLINE;
        }
        return TaskType.GENERIC;
    }

    public static int parseDelete(String toDelete) throws NumberFormatException, IndexOutOfBoundsException {
        return Integer.parseInt(toDelete.substring(7)) - 1;
    }

    public static int parseMark(String toMark) throws NumberFormatException, IndexOutOfBoundsException {
        return Integer.parseInt(toMark.substring(5)) - 1;
    }

    public static int parseUnmark(String toUnmark) throws NumberFormatException, IndexOutOfBoundsException {
        return Integer.parseInt(toUnmark.substring(7)) - 1;
    }

    public static String parseFind(String find) throws StringIndexOutOfBoundsException {
        return find.substring(5);
    }

    public static String parseToDo(String todo) throws StringIndexOutOfBoundsException{
        return todo.substring(5);
    }

    public static String[] parseDeadline(String deadline) throws StringIndexOutOfBoundsException, DateTimeParseException {
        String[] out = new String[2];
        deadline = deadline.substring(9);
        out[0] = deadline.substring(0, deadline.indexOf(" /by "));
        out[1] = deadline.substring(deadline.indexOf(" /by ") + 5);
        return out;
    }

    public static String[] parseEvent(String event) throws StringIndexOutOfBoundsException, DateTimeParseException {
        String[] out = new String[3];
        event = event.substring(6);
        int startPoint = event.indexOf(" /from ");
        int endPoint = event.indexOf(" /to ");
        out[0] = event.substring(0, startPoint);
        out[1] = event.substring(startPoint + 7, endPoint);
        out[2] = event.substring(endPoint + 5);
        return out;
    }
}
