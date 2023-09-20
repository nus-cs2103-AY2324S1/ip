package dukeutilities;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.UpdateCommand;

import exceptions.DukeException;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;


/**
 * The Parser class parses user input to create appropriate Command objects.
 * It handles different types of user commands and returns corresponding Command objects.
 * It also handles reading and parsing text from files.
 */
public class Parser {

    /**
     * Parses a line in the user's file text and returns the corresponding Task.
     *
     * @param line The full line in the file.
     * @return The task object represented by the text.
     */
    public static Task parseLine(String line) {
        String[] sections = line.split(" | ");
        int type = parseType(sections[0]);
        assert type >= 1 && type <= 3;
        boolean isDone = parseDone(sections[1]);
        String title = sections[2];
        String details = sections[3];
        switch (type) {
            case 1:
                return new ToDo(title, isDone);
            case 2:
                return new Deadline(title, isDone, details);
            default:
                String[] startEnd = details.split(" - ");
                assert startEnd.length == 2;
                return new Event(title, isDone, startEnd[0], startEnd[1]);
        }
    }

    /**
     * Parses a string value of a letter to determine task type.
     *
     * @param letter A string representing the task type.
     * @return An integer representing the task type.
     */
    private static int parseType(String letter) {
        switch (letter) {
            case "T":
                return 1;
            case "D":
                return 2;
            case "E":
                return 3;
            default:
                assert false;
                return 0;
        }
    }

    /**
     * Parses a string value of a number to determine if task is done.
     *
     * @param number A string representing the task status.
     * @return A Boolean representing the task status.
     */
    private static Boolean parseDone(String number) {
        int isComplete = Integer.parseInt(number);
        assert (isComplete == 1 || isComplete == 0);
        return isComplete == 1;
    }

    /**
     * Parses the user's full command and returns the corresponding Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.isEmpty()) {
            throw new DukeException("You entered nothing! Try again!");
        }
        String[] parts = fullCommand.split(" ", 2);
        String commandType = parts[0].toLowerCase();
        String commandArgs = parts.length > 1 ? parts[1] : "";
        switch (commandType) {
            case "list":
                return new ListCommand();
            case "find":
                return new FindCommand(commandArgs);
            case "todo":
                return createTaskCommand(commandArgs, "todo");
            case "deadline":
                return createTaskCommand(commandArgs, "deadline");
            case "event":
                return createTaskCommand(commandArgs, "event");
            case "delete":
                return createEditCommand(commandArgs, "delete");
            case "mark":
                return createEditCommand(commandArgs, "mark");
            case "unmark":
                return createEditCommand(commandArgs, "unmark");
            case "update":
                return createEditCommand(commandArgs, "update");
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("Sorry! Don't know what that is!");
        }
    }


    /**
     * Creates a Task command object according to the user's arugments and type.
     *
     * @param commandArgs The arguments of the command.
     * @param type        The type of the command.
     * @return A Command object representing the created command.
     * @throws DukeException If the command is invalid.
     */

    private static Command createTaskCommand(String commandArgs, String type) throws DukeException {
        if (commandArgs.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        }
        try {
            switch (type) {
                case "todo":
                    return new AddCommand(new ToDo(commandArgs));
                case "deadline":
                    return new AddCommand(new Deadline(commandArgs));
                case "event":
                    return new AddCommand(new Event(commandArgs));
                default:
                    throw new DukeException("Invalid task type: " + type);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please specify the deadline or event time!");
        }
    }

    /**
     * Creates an Edit command object according to the user's arugments and type.
     *
     * @param commandArgs The arguments of the command.
     * @param type        The type of the command.
     * @return A Command object representing the created command.
     * @throws DukeException If the command is invalid.
     */
    private static Command createEditCommand(String commandArgs, String type) throws DukeException {
        try {
            if (type.equals("unmark")) {
                return new UnmarkCommand(Integer.parseInt(commandArgs) - 1);
            } else if (type.equals("mark")) {
                return new MarkCommand(Integer.parseInt(commandArgs) - 1);
            } else if (type.equals("delete")) {
                return new DeleteCommand(Integer.parseInt(commandArgs) - 1);
            } else if (type.equals("update")) {
                return new UpdateCommand(commandArgs);
            } else {
                throw new DukeException("Unknown command!");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a valid numeric value for " + type + " command!");
        }
    }
}
