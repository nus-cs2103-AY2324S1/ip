package blip.parser;


import blip.exceptions.EmptyTaskNumberException;
import blip.exceptions.EmptyDescriptionException;
import blip.exceptions.DateTimeFormatException;
import blip.commands.Command;
import blip.commands.ByeCommand;
import blip.commands.MarkCommand;
import blip.commands.UnmarkCommand;
import blip.commands.ListCommand;
import blip.commands.DeleteCommand;
import blip.commands.FindCommand;
import blip.commands.ToDoCommand;
import blip.commands.DeadlineCommand;
import blip.commands.EventCommand;
import blip.commands.ExceptionCommand;
import blip.commands.InvalidCommand;



/**
 * Represents a parser that will parse user inputs that are Strings.
 */
public class BlipParser {
    /**
     * Parses string to an int to get the task index to do commands on.
     * @param input The string input to parse into int index
     * @return Integer index to do relevant commands on
     * @throws EmptyTaskNumberException If int task number is missing
     */
    public static int parseToGetIndex (String input) throws EmptyTaskNumberException {
        String[] components = input.split("\\s+", 2);
        assert components.length >= 2 : "Invalid input format for parseToGetIndex";
        // Missing Index.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyTaskNumberException("!!! Missing Task Number !!!");
        }
        int taskNum = Integer.parseInt(components[1]) - 1;
        return taskNum;
    }

    /**
     * Parses string to get the description of to do task.
     * @param input The string input to parse into description
     * @return String description of to do task
     * @throws EmptyDescriptionException If description of to do task is missing
     */
    public static String parseToDoInfo (String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 2);
        assert components.length >= 2 : "Invalid input format for parseToDoInfo";
        // Missing To Do Description.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing TO DO Description !!!\n");
        }
        return components[1];
    }

    /**
     * Parses string to get the deadline information of deadline task.
     * @param input The string input to parse into 2 components of deadline information
     * @return An array of string with deadline's description and date time
     * @throws EmptyDescriptionException If the description or date time is missing
     */
    public static String[] parseDeadlineInfo (String input) throws EmptyDescriptionException {
            String[] test = input.split("\\s+", 2);
            assert test.length >= 2 : "Invalid input format for parseDeadlineInfo";

            // Missing Deadline Description.
            if (test.length < 2 || test[1].equals("")) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Description !!!");
            }
            String[] components = test[1].split("\\s*/by\\s*");
            assert components.length >= 2 : "Invalid input format for parseDeadlineInfo";
            if (components.length < 2 || components[1].equals("")) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Date Time !!!");
            }
            String description = components[0];
            String deadlineDateTime = components[1];
            return new String[] {description, deadlineDateTime};

    }

    /**
     * Parses string to get the event information of event task.
     * @param input The string input to parse into 3 components of event information
     * @return An array of string with event's description and date time(s)
     * @throws EmptyDescriptionException If the description ot date time is missing
     */
    public static String[] parseEventInfo (String input) throws EmptyDescriptionException {
        String[] test = input.split("\\s+", 2);
        assert test.length >= 2 : "Invalid input format for parseEventInfo";

        // Missing Event Description.
        if (test.length < 2 || test[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing EVENT Description !!!");
        }
        String[] components = test[1].split(" /from | /to ");
        assert test.length >= 3 : "Invalid input format for parseEventInfo";
        if (components.length < 3 || components[1].equals("") || components[2].equals("")) {
            throw new EmptyDescriptionException("!!! Missing EVENT Start/End Date Time !!!");
        }
        String description = components[0];
        String eventStart = components[1];
        String eventEnd = components[2];
        return new String[] {description, eventStart, eventEnd};
    }

    /**
     * Parses string to get the find description.
     * @param input The string input to parse
     * @return String description to find tasks
     */
    public static String parseFindInfo (String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 2);
        assert components.length >= 2 : "Invalid input format for parseFindInfo";

        // Missing Find Description.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing FIND Description !!!\n");
        }
        return components[1];
    }


    /**
     * Parses the user input into different commands.
     *
     * @param input The string input to parse
     * @return The command related to user input
     */
    public Command parse(String input) {
        try {
            String[] parts = input.split(" ");

            String command = parts[0].toLowerCase();
            switch (command) {
                case "bye":
                    return new ByeCommand();
                case "list":
                    return new ListCommand();
                case "delete":
                    int indexToDelete = parseToGetIndex(input);
                    return new DeleteCommand(indexToDelete);
                case "todo":
                    String toDoDescription = parseToDoInfo(input);
                    return new ToDoCommand(toDoDescription);
                case "deadline":
                    String[] deadlineInfo = parseDeadlineInfo(input);
                    return new DeadlineCommand(deadlineInfo[0], DateConverter.convertToDateTime(deadlineInfo[1]));
                case "event":
                    String[] eventInfo = parseEventInfo(input);
                    return new EventCommand(eventInfo[0], DateConverter.convertToDateTime(eventInfo[1]),
                            DateConverter.convertToDateTime(eventInfo[2]));
                case "mark":
                    int indexToMark = parseToGetIndex(input);
                    return new MarkCommand(indexToMark);
                case "unmark":
                    int indexToUnmark = parseToGetIndex(input);
                    return new UnmarkCommand(indexToUnmark);
                case "find":
                    String findDescription = parseFindInfo(input);
                    return new FindCommand(findDescription);
                default:
                    return new InvalidCommand(input);
            }
        } catch (EmptyTaskNumberException e2) {
            return new ExceptionCommand(e2, e2.getMessage());
        } catch (EmptyDescriptionException e3) {
            return new ExceptionCommand(e3, e3.getMessage());
        } catch (DateTimeFormatException e4) {
            return new ExceptionCommand(e4, e4.getMessage());
        }
    }
}
