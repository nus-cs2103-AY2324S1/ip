package dukeutilities;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;

import exceptions.DukeException;

import task.Deadline;
import task.Event;
import task.ToDo;


/**
 * The Parser class parses user input to create appropriate Command objects.
 * It handles different types of user commands and returns corresponding Command objects.
 */
public class Parser {

    /**
     * Parses the user's full command and returns the corresponding Command object.
     *
     * @param fullCommand The full command entered by the user.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the command cannot be parsed or is invalid.
     */
//    public static Command parse(String fullCommand) throws DukeException {
//        if (fullCommand.isEmpty()) {
//            throw new DukeException("You entered nothing! Try again!");
//        } else if (fullCommand.equalsIgnoreCase("list")) {
//            return new ListCommand();
//        } else if (fullCommand.startsWith("find")) {
//            return new FindCommand(fullCommand);
//        } else if (fullCommand.startsWith("todo")) {
//            if (fullCommand.equals("todo")) {
//                throw new DukeException("Description cannot be empty!");
//            } else {
//                ToDo newTodo = new ToDo(fullCommand);
//                return new AddCommand(newTodo);
//            }
//        } else if (fullCommand.startsWith("deadline")) {
//            if (fullCommand.equals("deadline")) {
//                throw new DukeException("Description cannot be empty!");
//            } else {
//                try {
//                    Deadline newDeadline = new Deadline(fullCommand);
//                    return new AddCommand(newDeadline);
//                } catch (IndexOutOfBoundsException e) {
//                    throw new DukeException("Please specify the deadline!");
//                }
//            }
//        } else if (fullCommand.startsWith("event")) {
//            if (fullCommand.equals("event")) {
//                throw new DukeException("Description cannot be empty!");
//            } else {
//                try {
//                    Event newEvent = new Event(fullCommand);
//                    return new AddCommand(newEvent);
//                } catch (IndexOutOfBoundsException e) {
//                    throw new DukeException("Please specify both the start and end time!");
//                }
//            }
//        } else if (fullCommand.startsWith("delete")) {
//            try {
//                return new DeleteCommand(fullCommand);
//            } catch (NumberFormatException e) {
//                throw new DukeException("Please input the correct command!");
//            }
//        } else if (fullCommand.startsWith("mark")) {
//            try {
//                return new MarkCommand(fullCommand);
//            } catch (NumberFormatException e) {
//                throw new DukeException("Please input the correct command!");
//            }
//        } else if (fullCommand.startsWith("unmark")) {
//            try {
//                return new UnmarkCommand(fullCommand);
//            } catch (NumberFormatException e) {
//                throw new DukeException("Please input the correct command!");
//            }
//        } else if (fullCommand.equalsIgnoreCase("bye")) {
//            return new ExitCommand();
//        } else {
//            throw new DukeException("Sorry! Don't know what that is!");
//        }
//    }
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
            case "bye":
                return new ExitCommand();
            default:
                throw new DukeException("Sorry! Don't know what that is!");
        }
    }

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

    private static Command createEditCommand(String commandArgs, String type) throws DukeException {
        try {
            if (type.equals("unmark")) {
                return new UnmarkCommand(Integer.parseInt(commandArgs) - 1);
            } else if (type.equals("mark")) {
                return new MarkCommand(Integer.parseInt(commandArgs) - 1);
            } else {
                return new DeleteCommand(Integer.parseInt(commandArgs) - 1);
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a valid numeric value for " + type + " command!");
        }
    }
}
