package parser;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.EmptyCommand;
import command.ErrorCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.MiscCommand;
import command.UnmarkCommand;

/**
 * The `Parser` class is responsible for parsing user input commands into executable commands.
 */
public class Parser {

    /**
     * Parses the user input string and returns the corresponding command object.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed command.
     */
    public static Command parse(String str) {
        if (str.isEmpty()) {
            return new EmptyCommand();
        } else if (str.startsWith("todo")) {
            return parseTodoCommand(str);
        } else if (str.startsWith("deadline")) {
            return parseDeadlineCommand(str);
        } else if (str.startsWith("event")) {
            return parseEventCommand(str);
        } else if (str.equals("list")) {
            return new ListCommand();
        } else if (str.startsWith("delete")) {
            return parseDeleteCommand(str);
        } else if (str.startsWith("mark")) {
            return parseMarkCommand(str);
        } else if (str.startsWith("unmark")) {
            return parseUnmarkCommand(str);
        } else if (str.equals("bye")) {
            return new ByeCommand();
        } else if (str.startsWith("find")) {
            return parseFindCommand(str);
        } else {
            return new MiscCommand();
        }
    }

    /**
     * Parses a user input string as a ToDo command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed ToDo command.
     */
    private static Command parseTodoCommand(String str) {
        try {
            String[] split = str.split(" ");
            if (split.length < 2) {
                throw new IllegalArgumentException();
            } else {
                return new AddTodoCommand(str.substring(split[0].length()).trim());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Hey! I think you forget to enter the todo description "
                    + "or leave a space after the command!");
            return new ErrorCommand("Hey! I think you forget to enter the todo description!");
        }
    }

    /**
     * Parses a user input string as a Deadline command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed Deadline command.
     */
    private static Command parseDeadlineCommand(String str) {
        try {
            String[] split = str.split("/by");

            if (str.equals("deadline")) {
                throw new IllegalArgumentException(
                        "Hey! Please enter the task description or leave a space after the command!");
            }
            if (split.length < 2) {
                throw new IllegalArgumentException(
                        "Hey! Please provide a deadline for your task in this format dd/MM/yyyy HHmm!");
            }
            String formattedDate = parseAndFormatDateTime(split[1].trim());
            String taskDesc = split[0].trim().substring(8).trim();

            return new AddDeadlineCommand(taskDesc, formattedDate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user input string as an Event command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed Event command.
     */
    private static Command parseEventCommand(String str) {
        try {
            if (str.equals("event")) {
                throw new IllegalArgumentException(
                        "Hey! Please enter the event description or leave a space after the command!");
            }

            String[] split1 = str.split("/from");

            if (split1.length < 2) {
                throw new IllegalArgumentException(
                        "Hey! Please provide a time range for your event "
                                + "in this format /from dd/MM/yyyy HHmm /to HHmm");
            }

            String[] split2 = split1[1].split("/to");

            if (split2.length < 2) {
                throw new IllegalArgumentException("Hey! Please provide an end time for your event");
            }
            String formattedStartTime = parseAndFormatDateTime(split2[0].trim());
            String formattedEndTime = parseAndFormatDateTime(split2[1].trim());
            String taskDesc = split1[0].trim().substring(5).trim();
            return new AddEventCommand(taskDesc, formattedStartTime, formattedEndTime);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Parses a user input string as a Delete command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed Delete command.
     */
    private static Command parseDeleteCommand(String str) {
        int index = Integer.parseInt(str.substring(7));
        return new DeleteCommand(index);
    }

    /**
     * Parses a user input string as a Mark command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed Mark command.
     */
    private static Command parseMarkCommand(String str) {
        int index = Integer.parseInt(str.substring(5));
        return new MarkCommand(index);
    }

    /**
     * Parses a user input string as an Unmark command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed Unmark command.
     */
    private static Command parseUnmarkCommand(String str) {
        int index = Integer.parseInt(str.substring(7));
        return new UnmarkCommand(index);
    }

    /**
     * Parses a user input string as a Find command.
     *
     * @param str The user input string to be parsed.
     * @return A command object representing the parsed Find command.
     */
    private static Command parseFindCommand(String str) {
        String[] split = str.split(" ");
        if (split.length < 2) {
            return new ErrorCommand("Hey! Please provide a keyword");
        }
        return new FindCommand(split[1].trim());
    }

    /**
     * Parses and formats a date and time string into a specific format.
     *
     * @param inputDateTime The date and time string to be parsed and formatted.
     * @return A formatted date and time string.
     * @throws IllegalArgumentException If the input date and time string has an invalid format.
     */
    private static String parseAndFormatDateTime(String inputDateTime) {
        DateTime dateTime = new DateTime();
        String formattedDateTime = dateTime.formatDateTime(inputDateTime);
        if (formattedDateTime.equals("Invalid format")) {
            throw new IllegalArgumentException("Invalid date/time format");
        }
        return formattedDateTime;
    }
}
