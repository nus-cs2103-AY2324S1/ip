package chad.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import chad.command.AddCommand;
import chad.command.Command;
import chad.command.DeleteCommand;
import chad.command.ExitCommand;
import chad.command.FindCommand;
import chad.command.MarkCommand;
import chad.command.PrintListCommand;
import chad.command.UnknownCommand;
import chad.command.UnmarkCommand;
import chad.exception.CommandException;
import chad.exception.DeadlineException;
import chad.exception.DeleteException;
import chad.exception.EventException;
import chad.exception.FindException;
import chad.exception.LoadException;
import chad.exception.MarkException;
import chad.exception.ToDoException;
import chad.exception.UnmarkException;
import chad.task.Deadline;
import chad.task.Event;
import chad.task.Task;
import chad.task.ToDo;

/**
 * Converts a given String into Tasks and Commands.
 */
public class Parser {
    /**
     * Returns a Command with the given instructions.
     *
     * @param input The given instruction to be parsed.
     * @return Command object that correspond to the given instruction.
     * @throws CommandException if given input is in the wrong format.
     */
    public static Command parse(String input) throws CommandException {
        if (input.equals("list")) {
            return new PrintListCommand();
        } else if (input.startsWith("mark")) {
            return new MarkCommand(Parser.parseUserMark(input));
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(Parser.parseUserUnmark(input));
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(Parser.parseUserDelete(input));
        } else if (input.startsWith("find")) {
            String queryString = Parser.parseUserFind(input);
            return new FindCommand(queryString);
        } else if (input.startsWith("deadline")) {
            Deadline deadline = Parser.parseUserDeadline(input);
            return new AddCommand(deadline);
        } else if (input.startsWith("event")) {
            Event event = Parser.parseUserEvent(input);
            return new AddCommand(event);
        } else if (input.startsWith("todo")) {
            ToDo todo = Parser.parseUserToDo(input);
            return new AddCommand(todo);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else {
            return new UnknownCommand();
        }
    }

    /**
     * Returns a Deadline object based on the information from the local file.
     *
     * @param input The given information from local file.
     * @return Deadline object that correspond to the information given.
     */
    public static Deadline parseLoadDeadline(String input) throws LoadException {
        int byIndex = input.indexOf('|', 7);
        assert byIndex != -1 : "Unable to find byDate for given Deadline";

        String description = input.substring(8, byIndex - 1);
        String by = input.substring(byIndex + 2);

        LocalDateTime byDate;

        try {
            byDate = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw new LoadException();
        }

        Deadline deadline = new Deadline(description, byDate);

        if (input.charAt(4) == '1') {
            deadline.markAsDone();
        }

        return deadline;
    }

    /**
     * Returns a Event object based on the information from the local file.
     *
     * @param input The given information from local file.
     * @return Event object that correspond to the information given.
     */
    public static Event parseLoadEvent(String input) throws LoadException {
        int fromIndex = input.indexOf('|', 7);
        int toIndex = input.indexOf('|', fromIndex + 1);
        assert fromIndex != -1 : "Unable to find fromDate for given Event";
        assert toIndex != -1 : "Unable to find toDate for given Event";

        String description = input.substring(8, fromIndex - 1);
        String from = input.substring(fromIndex + 2, toIndex - 1);
        String to = input.substring(toIndex + 2);

        LocalDateTime fromDate;
        LocalDateTime toDate;

        try {
            fromDate = LocalDateTime.parse(from);
            toDate = LocalDateTime.parse(to);
        } catch (DateTimeParseException e) {
            throw new LoadException();
        }

        Event event = new Event(description, fromDate, toDate);

        if (input.charAt(4) == '1') {
            event.markAsDone();
        }

        return event;
    }

    /**
     * Returns a ToDo object based on the information from the local file.
     *
     * @param input The given information from local file.
     * @return ToDo object that correspond to the information given.
     */
    public static ToDo parseLoadToDo(String input) {
        String description = input.substring(8);

        ToDo todo = new ToDo(description);

        if (input.charAt(4) == '1') {
            todo.markAsDone();
        }

        return todo;
    }

    /**
     * Returns a Deadline object based on the user given input.
     *
     * @param input The user given input.
     * @return Deadline object that corresponds to the user given input.
     * @throws DeadlineException if given input is missing information or in wrong format.
     */
    public static Deadline parseUserDeadline(String input) throws DeadlineException {
        int byIndex = input.indexOf("/by");

        if (input.length() <= 9) {
            throw new DeadlineException();
        } else if (byIndex == -1) {
            throw new DeadlineException();
        }

        String description = input.substring(9, byIndex - 1);
        String by = input.substring(byIndex + 5);

        LocalDateTime byDate;

        try {
            byDate = LocalDateTime.parse(by);
        } catch (DateTimeParseException e) {
            throw new DeadlineException();
        }

        Deadline deadline = new Deadline(description, byDate);
        return deadline;
    }

    /**
     * Returns an Event object based on the user given input.
     *
     * @param input The user given input.
     * @return Event object that corresponds to the user given input.
     * @throws EventException if given input is missing information or in wrong format.
     */
    public static Event parseUserEvent(String input) throws EventException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (input.length() <= 6) {
            throw new EventException();
        } else if (fromIndex == -1 || toIndex == -1) {
            throw new EventException();
        } else if (fromIndex > toIndex) {
            throw new EventException();
        }

        String description = input.substring(6, fromIndex - 1);
        String from = input.substring(fromIndex + 7, toIndex - 1);
        String to = input.substring(toIndex + 5);

        LocalDateTime fromDate;
        LocalDateTime toDate;

        try {
            fromDate = LocalDateTime.parse(from);
            toDate = LocalDateTime.parse(to);
        } catch (DateTimeParseException e) {
            throw new EventException();
        }

        Event event = new Event(description, fromDate, toDate);
        return event;
    }

    /**
     * Returns a ToDo object based on the user given input.
     *
     * @param input The user given input.
     * @return ToDo object that corresponds to the user given input.
     * @throws ToDoException if given input is missing information or in wrong format.
     */
    public static ToDo parseUserToDo(String input) throws ToDoException {
        if (input.length() <= 5) {
            throw new ToDoException();
        }

        ToDo todo = new ToDo(input.substring(5));
        return todo;
    }

    /**
     * Returns the index of the Task to be mark based on user input.
     *
     * @param input The given user input.
     * @return The index of the Task the user wishes to mark.
     * @throws CommandException if given input is missing information or in wrong format.
     */
    public static int parseUserMark(String input) throws CommandException {
        int index = Integer.valueOf(input.substring(5)) - 1;

        if (input.length() <= 5) {
            throw new MarkException();
        }

        return index;
    }

    /**
     * Returns the index of Task to be unmark based on user input.
     *
     * @param input The given user input.
     * @return The index of the Task the user wishes to unmark.
     * @throws CommandException if given input is missing information or in wrong format.
     */
    public static int parseUserUnmark(String input) throws CommandException {
        int index = Integer.valueOf(input.substring(7)) - 1;

        if (input.length() <= 7) {
            throw new UnmarkException();
        }

        return index;
    }

    /**
     * Returns the index of the Task to be deleted based on user input.
     *
     * @param input The given user input.
     * @return The index of the Task the user wishes to delete.
     * @throws CommandException if given input is missing information or in wrong format.
     */
    public static int parseUserDelete(String input) throws CommandException {
        int index = Integer.valueOf(input.substring(7)) - 1;

        if (input.length() <= 7) {
            throw new DeleteException();
        }

        return index;
    }

    /**
     * Returns the query string to be searched based on user input.
     *
     * @param input The given user input.
     * @return The string the users wishes to search.
     * @throws CommandException if given input is missing information or in wrong format.
     */
    public static String parseUserFind(String input) throws CommandException {
        if (input.length() <= 5) {
            throw new FindException();
        }

        return input.substring(5);
    }

    /**
     * Returns the String of the given TaskList in the data file format.
     *
     * @param taskList The given TaskList to be parsed into data.
     * @return The String of the given TaskList in the data file format.
     */
    public static String parseTaskListToData(TaskList taskList) {
        String newData = "";

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);

            newData += task.toDataFormatString();
            newData += System.lineSeparator();
        }
        return newData;
    }
}
