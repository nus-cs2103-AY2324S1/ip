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
import blip.commands.PriorityCommand;
import blip.priority.Priority;

/**
 * Represents a parser that will parse user inputs that are Strings.
 */
public class BlipParser {
    /**
     * Parses string to an int to get the task index to do commands on.
     *
     * @param input The string input to parse into int index
     * @return Integer index to do relevant commands on
     * @throws EmptyTaskNumberException If int task number is missing
     */
    public static int parseToGetIndex(String input) throws EmptyTaskNumberException {
        String[] components = input.split("\\s+", 3);
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
     *
     * @param input The string input to parse into description
     * @return String description of to do task
     * @throws EmptyDescriptionException If description of to do task is missing
     */
    public static String[] parseToDoInfo(String input) throws EmptyDescriptionException {
        String[] test = input.split("\\s+", 2);
        assert test.length >= 2 : "Invalid input format for parseToDoInfo";
        // For cases such as "todo"
        if (test.length < 2 || test[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing TO DO Details !!!\n");
        }

        // Initialize description to be description after todo
        //  and priority string to be default LOW.
        String description = test[1];
        String priorityString = "low";

        // For cases such as "todo eat /priority LOW"
        if (input.contains("/priority")) {
            String[] components = test[1].split("\\s+/priority\\s+");
            assert components.length >= 2 : "Invalid input format for parseToDoInfo";
            if (components.length < 2) {
                throw new EmptyDescriptionException("!!! Missing TO DO Details!!!\n");
            }
            description = components[0];
            priorityString = components[1];
        }

        return new String[] {description, priorityString};
    }

    /**
     * Parses string to get the deadline information of deadline task.
     *
     * @param input The string input to parse into 2 components of deadline information
     * @return An array of string with deadline's description and date time
     * @throws EmptyDescriptionException If the description or date time is missing
     */
    public static String[] parseDeadlineInfo(String input) throws EmptyDescriptionException {
        String[] test = input.split("\\s+", 2);
        assert test.length >= 2 : "Invalid input format for parseDeadlineInfo";
        if (test.length < 2 || test[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing DEADLINE Details !!!\n");
        }

        // Initialize description and priority string.
        String description = "";
        String deadlineDateTime = "";
        String priorityString = "low";

        // Has Priority included
        if (input.contains("/priority")) {
            String[] components = test[1].split(" /by | /priority ");
            assert components.length >= 3 : "Invalid input format for parseDeadlineInfo";
            if (components.length < 3) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Details !!!");
            }
            if (components[2].equals("")) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Details !!!\n");
            }

            description = components[0];
            deadlineDateTime = components[1];
            priorityString = components[2];
        }
        // No priority included, set to default LOW
        if (!input.contains("/priority")) {
            String[] components = test[1].split(" /by ");
            if (components.length < 2) {
                throw new EmptyDescriptionException("!!! Missing DEADLINE Details !!!\n");
            }

            description = components[0];
            deadlineDateTime = components[1];
        }

        return new String[] {description, deadlineDateTime, priorityString};
    }

    /**
     * Parses string to get the event information of event task.
     *
     * @param input The string input to parse into 3 components of event information
     * @return An array of string with event's description and date time(s)
     * @throws EmptyDescriptionException If the description ot date time is missing
     */
    public static String[] parseEventInfo(String input) throws EmptyDescriptionException {
        String[] test = input.split("\\s+", 2);
        assert test.length >= 2 : "Invalid input format for parseEventInfo";
        if (test.length < 2 || test[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing EVENT Details !!!\n");
        }
        String description = "";
        String eventStart = "";
        String eventEnd = "";
        String priorityString = "low";

        if (input.contains("/priority")) {
            String[] components = test[1].split(" /from | /to | /priority ");
            assert test.length >= 4 : "Invalid input format for parseEventInfo";
            if (components.length < 4) {
                throw new EmptyDescriptionException("!!! Missing EVENT Details !!!\n");
            }
            description = components[0];
            eventStart = components[1];
            eventEnd = components[2];
            priorityString = components[3];
        }
        if (!input.contains("/priority")) {
            String[] components = test[1].split(" /from | /to ");
            assert test.length >= 3 : "Invalid input format for parseEventInfo";
            if (components.length < 3) {
                throw new EmptyDescriptionException("!!! Missing EVENT Details !!!\n");
            }
            description = components[0];
            eventStart = components[1];
            eventEnd = components[2];
        }

        return new String[] {description, eventStart, eventEnd, priorityString};
    }

    /**
     * Parses string to get the find description.
     *
     * @param input The string input to parse
     * @return String description to find tasks
     */
    public static String parseFindInfo(String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 2);
        assert components.length >= 2 : "Invalid input format for parseFindInfo";

        // Missing Find Description.
        if (components.length < 2 || components[1].equals("")) {
            throw new EmptyDescriptionException("!!! Missing FIND Keyword(s) !!!\n");
        }
        return components[1];
    }

    /**
     * Parses string to get priority level in string representation.
     *
     * @param input String of priority command to set priority of a task
     * @return String representation of priority level: low/medium/high
     * @throws EmptyDescriptionException If the priority level is missing
     */
    public static String parsePriorityInfo(String input) throws EmptyDescriptionException {
        String[] components = input.split("\\s+", 3);
        assert components.length >= 3 : "Invalid input format for parsePriorityInfo";
        // Missing priority description.
        if (components.length < 3 || components[2].equals("")) {
            throw new EmptyDescriptionException("!!! Missing PRIORITY Level !!!\n");
        }

        // return the priority type in string
        return components[2];
    }

    /**
     * Converts the string representation of priority levels low/medium/high to type Priority respectively.
     *
     * @param input String representation of priority level: low/medium/high
     * @return A Priority of LOW/MEDIUM/HIGH
     */
    public static Priority convertToPriority(String input) {
        switch (input.toLowerCase()) {
            case "low":
                return Priority.LOW;
            case "medium":
                return Priority.MEDIUM;
            case "high":
                return Priority.HIGH;
            default:
                return Priority.LOW;
        }
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
                    String[] toDoDescription = parseToDoInfo(input);
                    return new ToDoCommand(toDoDescription[0], convertToPriority(toDoDescription[1]));
                case "deadline":
                    String[] deadlineInfo = parseDeadlineInfo(input);
                    return new DeadlineCommand(deadlineInfo[0]
                            , DateConverter.convertToDateTime(deadlineInfo[1])
                            , convertToPriority(deadlineInfo[2]));
                case "event":
                    String[] eventInfo = parseEventInfo(input);
                    return new EventCommand(eventInfo[0], DateConverter.convertToDateTime(eventInfo[1])
                            , DateConverter.convertToDateTime(eventInfo[2])
                            , convertToPriority(eventInfo[3]));
                case "mark":
                    int indexToMark = parseToGetIndex(input);
                    return new MarkCommand(indexToMark);
                case "unmark":
                    int indexToUnmark = parseToGetIndex(input);
                    return new UnmarkCommand(indexToUnmark);
                case "find":
                    String findDescription = parseFindInfo(input);
                    return new FindCommand(findDescription);
                case "priority":
                    int indexToPrioritise = parseToGetIndex(input);
                    String priorityString = parsePriorityInfo(input);
                    return new PriorityCommand(indexToPrioritise, convertToPriority(priorityString));
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
