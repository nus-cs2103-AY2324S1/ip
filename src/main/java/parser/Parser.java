package parser;

import commands.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * The `Parser` class provides methods for parsing user input and converting it into commands or tasks.
 * It also includes a method for parsing task data from a save file format.
 */
public class Parser {

    /**
     * Parses the user input and returns an appropriate command object.
     *
     * @param input The user input string to be parsed.
     * @return A command object based on the parsed input.
     */
    public static Command parseCommand(String input) {
        String[] split = input.split(" ", 2);
        Command c = null;
        switch (split[0]) {
            case "bye":
                c = checkExit(split);
                break;
            case "todo":
                c = checkTodo(split);
                break;
            case "event":
                c = checkEvent(split);
                break;
            case "deadline":
                c = checkDeadline(split);
                break;
            case "unmark":
                c = checkUnmark(split);
                break;
            case "mark":
                c = checkMark(split);
                break;
            case "delete":
                c = checkDelete(split);
                break;
            case "listout":
                c = checkListout(split);
                break;
            default:
                c = new IncorrectCommand("idk what u saying.");

        }
        return c;
    }

    /**
     * Checks the user input for a "listout" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return A "ListoutCommand" if the input is a valid listout command, otherwise an "IncorrectCommand."
     */
    private static Command checkListout(String[] split) {
        if (split.length == 1) {
            return new ListoutCommand();
        } else {
            return new IncorrectCommand("why are u telling me things after listout.");
        }
    }

    /**
     * Checks the user input for a "mark" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return A "MarkCommand" if the input is a valid mark command, otherwise an "IncorrectCommand."
     */
    private static Command checkMark(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task to MARK!");
        }
        if (!Character.isDigit(split[1].charAt(split[1].charAt(0)))) {
            return new IncorrectCommand("Enter task number u wish to MARK");
        }

        int taskNumber = Integer.parseInt(split[1]);
        if (taskNumber <= 0) {
            return new IncorrectCommand("Enter valid task number.");
        } else {
            return new MarkCommand(taskNumber);
        }

    }

    /**
     * Checks the user input for an "exit" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return An "ExitCommand" if the input is a valid exit command, otherwise an "IncorrectCommand."
     */
    public static Command checkExit(String[] split) {
        if (split.length > 1) {
            return new IncorrectCommand("Cannot have anything after bye");
        }
        return new ExitCommand();
    }

    /**
     * Checks the user input for a "todo" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return A "TodoCommand" if the input is a valid todo command, otherwise an "IncorrectCommand."
     */
    public static Command checkTodo(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task.");
        }
        return new AddCommand(split[1]);
    }

    /**
     * Checks the user input for a "deadline" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return A "DeadlineCommand" if the input is a valid deadline command, otherwise an "IncorrectCommand."
     */
    public static Command checkDeadline(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task and the deadline it's due by. BUCK UP!");
        }

        String[] deadline = split[1].split(" /by ", 2);
        if (deadline[1].isBlank() || deadline[0].isBlank()) {
            return new IncorrectCommand("Please enter a valid deadline.");
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new AddCommand(deadline[0], LocalDateTime.parse(deadline[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Enter in format yyyy-mm-dd 24:00");
        }
    }

    /**
     * Checks the user input for an "event" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return An "EventCommand" if the input is a valid event command, otherwise an "IncorrectCommand."
     */
    public static Command checkEvent(String[] split) {
        if (split.length == 1 || !split[1].contains(" /from ")) {
            return new IncorrectCommand("You didn't tell me ur task and the time period it's due by. BUCK UP!");
        }
        try {
            String[] task = split[1].split(" /from ", 2);
            String[] from = task[1].split(" /to ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new AddCommand(task[0], LocalDateTime.parse(from[0], formatter), LocalDateTime.parse(from[1], formatter));
        } catch (DateTimeParseException e) {
            return new IncorrectCommand("Enter in format yyyy-mm-dd 24:00");
        }
    }

    /**
     * Checks the user input for an "unmark" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return An "UnmarkCommand" if the input is a valid unmark command, otherwise an "IncorrectCommand."
     */
    public static Command checkUnmark(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task to UNMARK!");
        }
        if (!Character.isDigit(split[1].charAt(split[1].charAt(0)))) {
            return new IncorrectCommand("Enter task number u wish to unmark");
        }

        int taskNumber = Integer.parseInt(split[1]);
        if (taskNumber <= 0) {
            return new IncorrectCommand("Enter valid task number.");
        } else {
            return new UnmarkCommand(taskNumber);
        }

    }

    /**
     * Checks the user input for a "delete" command and returns the corresponding command object.
     *
     * @param split The user input string split into tokens.
     * @return A "DeleteCommand" if the input is a valid delete command, otherwise an "IncorrectCommand."
     */
    public static Command checkDelete(String[] split) {
        if (split.length == 1) {
            return new IncorrectCommand("You didn't tell me ur task to DELETE!");
        }
        if (!Character.isDigit(split[1].charAt(split[1].charAt(0)))) {
            return new IncorrectCommand("Enter task number u wish to DELETE");
        }
        int taskNumber = Integer.parseInt(split[1]);
        if (taskNumber <= 0) {
            return new IncorrectCommand("Enter valid task number FOR DELETE.");
        } else {
            return new DeleteCommand(taskNumber);
        }
    }

    /**
     * Parses a task in the save file format and returns a corresponding task object.
     *
     * @param sf The string in save file format representing a task.
     * @return A task object based on the parsed save file format.
     */
    public static Task parseFomSaveFormat(String sf) {
        String[] parts = sf.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String description = parts[2];
        if (type.equals("T")) {
            Task task = new ToDo(description);
            task.setStatus(isDone);
            return task;
        }
        String date = parts[3];
        if (type.equals("D")) {
            Task task = new Deadline(description, LocalDateTime.parse(date, formatter));
            task.setStatus(isDone);
            return task;
        }
        if (type.equals("E")) {
            String[] timeline = date.split("-", 2);
            String from = timeline[0];
            String to = timeline[1];
            Task task = new Event(description, LocalDateTime.parse(from, formatter), LocalDateTime.parse(to, formatter));
            task.setStatus(isDone);
            return task;
        }
        return null;
    }
}

