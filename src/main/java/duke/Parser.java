package duke;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.CommandDetailException;
import duke.exceptions.CommandNotRecognizedException;
import duke.exceptions.TimeParsingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


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
        String trimedInput = input.trim();
        String[] splitInput = trimedInput.split(" ", 2);
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
            default: {
                Task task = parseTask(trimedInput);
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
                return new ToDo(splitInput[1]);
            case "deadline": {
                String[] deadlineParts = splitInput[1].split(" /by ", 2);
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            }
            case "event": {
                String[] eventParts = splitInput[1].split(" /from ", 2);
                String eventName = eventParts[0];
                String[] eventTimes = eventParts[1].split(" /to ", 2);
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
