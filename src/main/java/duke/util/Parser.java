package duke.util;

import duke.CheeException;
import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
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
        }
        return new InvalidCommand();
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
        String description = command.substring(3);
        String name = description.substring(4);
        assert name.length() > 0 : "description should not be empty";
        Todo task = new Todo(name);
        if (description.startsWith("[X]")) {
            task.setStatus(true);
            assert task.getStatusIcon().equals("X") : "task should be marked";
        } else {
            task.setStatus(false);
            assert task.getStatusIcon().equals(" ") : "task should be unmarked";
        }
        return task;
    }

    public static Deadline parseFileDeadline(String command) {
        String description = command.substring(3);
        int endDescription = description.indexOf("(by: ");
        String name = description.substring(4, endDescription);
        int length = description.length();
        String time = description.substring(endDescription + 5, length - 1);
        assert time.equals("") : "time should not be empty";
        LocalDate date = LocalDate.parse(time);
        Deadline task = new Deadline(name, date);
        if (description.startsWith("[X]")) {
            task.setStatus(true);
            assert task.getStatusIcon().equals("X") : "task should be marked";
        } else {
            task.setStatus(false);
            assert task.getStatusIcon().equals(" ") : "task should be unmarked";
        }
        return task;
    }

    public static Event parseFileEvent(String command) {
        String description = command.substring(3);
        int endDescription = description.indexOf("(from: ");
        String name = description.substring(4, endDescription);
        int endFrom = description.indexOf("to: ");
        String from = description.substring(endDescription + 7, endFrom - 1);
        assert from.equals("") : "from value should not be empty";
        String to = description.substring(endFrom + 4, description.length() - 1);
        assert to.equals("") : "to value should not be empty";
        LocalDate startDate = LocalDate.parse(from);
        LocalDate endDate = LocalDate.parse(to);
        Event task = new Event(name, startDate, endDate);
        if (description.startsWith("[X]")) {
            task.setStatus(true);
            assert task.getStatusIcon().equals("X") : "task should be marked";
        } else {
            task.setStatus(false);
            assert task.getStatusIcon().equals(" ") : "task should be unmarked";
        }
        return task;
    }

}


