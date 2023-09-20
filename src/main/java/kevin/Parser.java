package kevin;

import kevin.commands.AddCommand;
import kevin.commands.ClearCommand;
import kevin.commands.Command;
import kevin.commands.DeleteCommand;
import kevin.commands.ExitCommand;
import kevin.commands.FindCommand;
import kevin.commands.ListCommand;
import kevin.commands.MarkCommand;
import kevin.commands.SortCommand;
import kevin.commands.UnmarkCommand;
import kevin.exceptions.CommandDetailException;
import kevin.exceptions.CommandNotRecognizedException;
import kevin.exceptions.TimeParsingException;
import kevin.task.Deadline;
import kevin.task.Event;
import kevin.task.Task;
import kevin.task.ToDo;


/**
 * Represents a parser for interpreting user input commands.
 * The parser is responsible for translating string input into executable command objects.
 */
public class Parser {

    public Parser() {
    }

    /**
     * Parses the user input string and translates it into an executable command object.
     *
     * @param input The string input from the user.
     * @return A Command object representing the user's intent.
     * @throws TimeParsingException          If there's an error in parsing the time for tasks.
     * @throws CommandNotRecognizedException If the entered command is not recognized.
     * @throws CommandDetailException        If there's an error in the command details.
     */
    public Command parse(String input)
            throws TimeParsingException, CommandNotRecognizedException, CommandDetailException {
        String trimmedInput = input.trim();
        String[] splitInput = trimmedInput.split(" ", 2);
        String command = splitInput[0].toLowerCase();
        try {
            switch (command) {
            case "bye": {
                return new ExitCommand();
            }
            case "list": {
                return new ListCommand();
            }
            case "mark": {
                int markIndex = Integer.parseInt(splitInput[1]) - 1;
                return new MarkCommand(markIndex);
            }
            case "delete": {
                int deleteIndex = Integer.parseInt(splitInput[1]) - 1;
                return new DeleteCommand(deleteIndex);
            }
            case "unmark": {
                int markIndex = Integer.parseInt(splitInput[1]) - 1;
                return new UnmarkCommand(markIndex);
            }
            case "find": {
                String keyword = splitInput[1];
                return new FindCommand(keyword);
            }
            case "clear": {
                return new ClearCommand();
            }
            case "sort": {
                return new SortCommand();
            }
            default: {
                Task task = parseTask(trimmedInput);
                if (task != null) {
                    return new AddCommand(task);
                } else {
                    throw new CommandNotRecognizedException("I'm sorry, but I don't know what " + input + " means :-(");
                }
            }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! Missing details for command: " + command + ".");
        } catch (NumberFormatException e) {
            throw new CommandDetailException("OOPS!!! Please enter a valid number for the index.");
        }
    }

    /**
     * Helper method to parse tasks from the input string.
     * Used for commands that involve creating tasks like ToDo, Deadline, and Event.
     *
     * @param input The string input from the user for creating tasks.
     * @return A Task object representing the specified task, or null if the command doesn't correspond to a task.
     * @throws TimeParsingException   If there's an error in parsing the time for tasks.
     * @throws CommandDetailException If there's an error in the command details.
     */
    protected Task parseTask(String input) throws TimeParsingException, CommandDetailException {
        String[] splitInput = input.split(" ", 2);
        String command = splitInput[0].toLowerCase();

        try {
            switch (command) {
            case "todo":
                //assert splitInput.length == 2 : "ToDo should have a description";
                return new ToDo(splitInput[1]);
            case "deadline": {
                //assert splitInput.length == 2 : "Deadline should have a description";
                String[] deadlineParts = splitInput[1].split(" /by ", 2);
                //assert deadlineParts.length == 2 : "Deadline should have a time";
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            }
            case "event": {
                String[] eventParts = splitInput[1].split(" /from ", 2);
                //assert eventParts.length == 2 : "Event should have a time";
                String eventName = eventParts[0];
                String[] eventTimes = eventParts[1].split(" /to ", 2);
                //assert eventTimes.length == 2 : "Event should have a start and end time";
                return new Event(eventName, eventTimes[0], eventTimes[1]);
            }
            default:
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CommandDetailException("OOPS!!! The description of a " + command + " cannot be understood.");
        }
    }
}
