package duke.util;

import duke.CheeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;

public class Parser {

    /**
     * Parse through the String command and return the correct type of Command.
     * @param command String that has the String input from the user.
     * @return A Command instance.
     */
    public static Command parse(String command) {
        if (command.equals("list")) {
            return new ListCommand();
        } else if (command.startsWith("mark")) {
            return new MarkCommand(parseUserIndex(command));
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(parseUserIndex(command));
        } else if (command.startsWith("todo")) {
            return new AddCommand(parseUserTodo(command));
        } else if (command.startsWith("deadline")) {
            return new AddCommand(parseUserDeadline(command));
        } else if (command.startsWith("event")) {
            return new AddCommand(parseUserEvent(command));
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(parseUserIndex(command));
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.startsWith("find")) {
            return new FindCommand(command.substring(5));
        } else if (command.startsWith("priority")) {
            return new PriorityCommand(parseUserPriority(command), parseUserIndex(command));
        }
        return new InvalidCommand();
    }

    private static Task.Prioritylist parseUserPriority(String command) {
        String priorityString = command.substring(8);
        if (priorityString.contains("HIGH")) {
            return Task.Prioritylist.HIGH;
        } else if (priorityString.contains("NORMAL")) {
            return Task.Prioritylist.NORMAL;
        } else if (priorityString.contains("LOW")) {
            return Task.Prioritylist.LOW;
        } else {
            return null;
        }
    }

    private static int parseUserIndex(String command) {
        int index = command.length() - 1;
        return command.charAt(index) - 48 - 1;
    }

    private static Todo parseUserTodo(String command) {
        try {
            if (command.substring(4).isEmpty()) {
                throw new CheeException("OOPS!!! The description of a todo cannot be empty.");
            }
        } catch (CheeException e) {
            System.out.println(e.getMessage());
        }
        Todo instance;
        String description = command.substring(5);
        instance = new Todo(description);
        return instance;
    }

    private static Deadline parseUserDeadline(String command) {
        int index = command.indexOf(47);
        String description = command.substring(9, index - 1);
        String time = command.substring(index + 4);
        LocalDate day = LocalDate.parse(time);
        return new Deadline(description, day);
    }

    private static Event parseUserEvent(String command) {
        int endOfDescription = command.indexOf(47);
        String description = command.substring(6, endOfDescription);
        String duration = command.substring(endOfDescription + 6);
        int startOfEndTime = duration.indexOf(47);
        String from = duration.substring(0, startOfEndTime - 1);
        String to = duration.substring(startOfEndTime + 4);
        LocalDate startDate = LocalDate.parse(from);
        LocalDate endDate = LocalDate.parse(to);
        return new Event(description, startDate, endDate);
    }

    public static Todo parseFileTodo(String command) {
        String description = command.substring(4);
        if (description.startsWith("HIGH") && command.startsWith("[X]")) {
            return createPrioritisedTodo(command, true, 9, Task.Prioritylist.HIGH);
        } else if (description.startsWith("HIGH") && command.startsWith("[ ]")) {
            return createPrioritisedTodo(command, false, 9, Task.Prioritylist.HIGH);
        } else if (description.startsWith("NORMAL") && command.startsWith("[X]")) {
            return createPrioritisedTodo(command, true, 11, Task.Prioritylist.NORMAL);
        } else if (description.startsWith("NORMAL") && command.startsWith("[ ]")) {
            return createPrioritisedTodo(command, false, 11, Task.Prioritylist.NORMAL);
        } else if (description.startsWith("LOW") && command.startsWith("[X]")) {
            return createPrioritisedTodo(command, true, 8, Task.Prioritylist.LOW);
        } else if (description.startsWith("LOW") && command.startsWith("[ ]")) {
            return createPrioritisedTodo(command, false, 8, Task.Prioritylist.LOW);
        } else {
            return null;
        }
    }

    public static Todo createPrioritisedTodo(String command, boolean mark, int stringIndex, Task.Prioritylist priority) {
        String name = command.substring(stringIndex);
        Todo task = new Todo(name);
        task.setStatus(mark);
        task.setPriority(priority);
        return task;
    }

    public static Deadline parseFileDeadline(String command) {
        String description = command.substring(4);
        if (description.startsWith("HIGH") && command.startsWith("[X]")) {
            return createPrioritisedDeadline(description, true, 5, Task.Prioritylist.HIGH);
        } else if (description.startsWith("HIGH") && command.startsWith("[ ]")) {
            return createPrioritisedDeadline(description, false, 5, Task.Prioritylist.HIGH);
        } else if (description.startsWith("NORMAL") && command.startsWith("[X]")) {
            return createPrioritisedDeadline(description, true, 7, Task.Prioritylist.NORMAL);
        } else if (description.startsWith("NORMAL") && command.startsWith("[ ]")) {
            return createPrioritisedDeadline(description, false, 7, Task.Prioritylist.NORMAL);
        } else if (description.startsWith("LOW") && command.startsWith("[X]")) {
            return createPrioritisedDeadline(description, true, 4, Task.Prioritylist.LOW);
        } else if (description.startsWith("LOW") && command.startsWith("[ ]")) {
            return createPrioritisedDeadline(description, false, 4, Task.Prioritylist.LOW);
        } else {
            return null;
        }
    }

    public static Deadline createPrioritisedDeadline(String command, boolean mark, int stringIndex, Task.Prioritylist priority) {
        int endDescription = command.indexOf("(by: ");
        String description = command.substring(stringIndex, endDescription);
        String time = command.substring(endDescription + 5, command.length() - 1);
        assert time.equals("") : "time should not be empty";
        Deadline task = new Deadline(description, LocalDate.parse(time));
        task.setStatus(mark);
        task.setPriority(priority);
        return task;
    }

    public static Event parseFileEvent(String command) {
        String description = command.substring(4);
        if (description.startsWith("HIGH") && command.startsWith("[X]")) {
            return createPrioritisedEvent(description, true, 5, Task.Prioritylist.HIGH);
        } else if (description.startsWith("HIGH") && command.startsWith("[ ]")) {
            return createPrioritisedEvent(description, false, 5, Task.Prioritylist.HIGH);
        } else if (description.startsWith("NORMAL") && command.startsWith("[X]")) {
            return createPrioritisedEvent(description, true, 7, Task.Prioritylist.NORMAL);
        } else if (description.startsWith("NORMAL") && command.startsWith("[ ]")) {
            return createPrioritisedEvent(description, false, 7, Task.Prioritylist.NORMAL);
        } else if (description.startsWith("LOW") && command.startsWith("[X]")) {
            return createPrioritisedEvent(description, true, 4, Task.Prioritylist.LOW);
        } else if (description.startsWith("LOW") && command.startsWith("[ ]")) {
            return createPrioritisedEvent(description, false, 4, Task.Prioritylist.LOW);
        } else {
            return null;
        }
    }

    public static Event createPrioritisedEvent(String command, boolean mark, int stringIndex, Task.Prioritylist priority) {
        int endDescription = command.indexOf("(from: ");
        String description = command.substring(stringIndex, endDescription);
        int endFrom = command.indexOf("to: ");
        String from = command.substring(endDescription + 7, endFrom - 1);
        assert from.equals("") : "from value should not be empty";
        String to = command.substring(endFrom + 4, command.length() - 1);
        assert to.equals("") : "to value should not be empty";
        Event task = new Event(description, LocalDate.parse(from), LocalDate.parse(to));
        task.setStatus(mark);
        task.setPriority(priority);
        return task;
    }

}


