package monke;

import monke.commands.Command;
import monke.commands.DeadlineCommand;
import monke.commands.DeleteCommand;
import monke.commands.EventCommand;
import monke.commands.ExitCommand;
import monke.commands.FindCommand;
import monke.commands.ListCommand;
import monke.commands.MarkCommand;
import monke.commands.TodoCommand;
import monke.commands.UnmarkCommand;
import monke.tasks.Deadline;
import monke.tasks.Event;
import monke.tasks.Task;
import monke.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Parser class is responsible for parsing user input and data from storage.
 */
public class Parser {
    /**
     * Parses a string containing loaded data and returns a Task object.
     *
     * @param data The data in string format.
     * @return A Task object parsed from the loaded data.
     * @throws MonkeException If data provided cannot be parsed.
     */
    public static Task parseLoadedData(String data) throws MonkeException {
        String[] tmp = data.split(" \\| ");
        String taskType = tmp[0];
        String isDone = tmp[1];
        String description = tmp[2];
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            String dateString = tmp[3];
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime date = LocalDateTime.parse(dateString, formatter);
            task = new Deadline(description, date);
            break;
        case "E":
            String start = tmp[3];
            String end = tmp[4];
            task = new Event(description, start, end);
            break;
        default:
            throw new MonkeException("Invalid file data");
        }
        if (isDone.equals("1")) {
            task.mark();
        }
        return task;
    }

    /**
     * Parses a full user command and returns the corresponding Command object.
     *
     * @param fullCommand The full user command.
     * @return A Command object which can execute the user command.
     * @throws MonkeException If the command is not recognized or has invalid arguments.
     */
    public static Command parse(String fullCommand) throws MonkeException {
        String[] temp = fullCommand.split(" ", 2);
        String command = temp[0];
        String args = temp.length > 1 ? temp[1] : "";
        switch (command) {
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return parseMark(args);
        case UnmarkCommand.COMMAND_WORD:
            return parseUnmark(args);
        case TodoCommand.COMMAND_WORD:
            return parseTodo(args);
        case DeadlineCommand.COMMAND_WORD:
            return parseDeadline(args);
        case EventCommand.COMMAND_WORD:
            return parseEvent(args);
        case DeleteCommand.COMMAND_WORD:
            return parseDelete(args);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case FindCommand.COMMAND_WORD:
            return parseFindCommand(args);
        default:
            throw new MonkeException("OOGA??!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses mark command and returns a MarkCommand object.
     *
     * @param args The arguments of the mark command.
     * @return A MarkCommand object that can execute the mark command.
     * @throws MonkeException If no argument provided or argument is not a number.
     */
    public static MarkCommand parseMark(String args) throws MonkeException {
        if (args.isBlank()) {
            throw new MonkeException("OOGA BOOGA!! Please provide a list number");
        }
        try {
            Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! Please provide a list number");
        }
        return new MarkCommand(args);
    }

    /**
     * Parses unmark command and returns an UnmarkCommand object.
     *
     * @param args The arguments of the unmark command.
     * @return A UnmarkCommand object that can execute the unmark command.
     * @throws MonkeException If no argument provided or argument is not a number.
     */
    public static UnmarkCommand parseUnmark(String args) throws MonkeException {
        if (args.isBlank()) {
            throw new MonkeException("OOGA BOOGA!! Please provide a list number");
        }
        try {
            Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! Please provide a list number");
        }
        return new UnmarkCommand(args);
    }

    /**
     * Parses todo command and returns an TodoCommand object.
     *
     * @param args The arguments of the todo command.
     * @return A TodoCommand object that can execute the todo command.
     * @throws MonkeException If no argument provided or whitespace given as argument.
     */
    public static TodoCommand parseTodo(String args) throws MonkeException {
        if (args.isBlank()) {
            throw new MonkeException("OOGA BOOGA!! The description of a todo cannot be empty.");
        }
        return new TodoCommand(args);
    }

    /**
     * Parses deadline command and returns an DeadlineCommand object.
     *
     * @param args The arguments of the deadline command.
     * @return A DeadlineCommand object that can execute the deadline command.
     * @throws MonkeException If no arguments provided or arguments not in (task) /by (datetime) format.
     */
    public static DeadlineCommand parseDeadline(String args) throws MonkeException {
        String[] tmp = args.split(" /by ", 2);
        if (tmp.length < 2 || tmp[0].isBlank() || tmp[1].isBlank()) {
            throw new MonkeException("You must format your deadline like this:\n" +
                    "\t\tdeadline <task> /by <deadline>");
        }
        String description = tmp[0];
        String dateString = tmp[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        try {
            LocalDateTime date = LocalDateTime.parse(dateString, formatter);
            return new DeadlineCommand(description, date);
        } catch (DateTimeParseException e) {
            throw new MonkeException("Format your deadline in yyyy-MM-dd HHmm format");
        }
    }

    /**
     * Parses event command and returns an EventCommand object.
     *
     * @param args The arguments of the event command.
     * @return A EventCommand object that can execute the event command.
     * @throws MonkeException If no arguments provided or arguments not in (task) /from (start) /to (end) format.
     */
    public static EventCommand parseEvent(String args) throws MonkeException {
        String[] tmp = args.split(" /from ", 2);
        String description = tmp[0];
        if (tmp.length < 2 || tmp[0].isBlank() || tmp[1].isBlank()) {
            throw new MonkeException("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>");
        }
        String[] tmp2 = tmp[1].split(" /to ", 2);
        if (tmp2.length < 2 || tmp2[0].isBlank() || tmp2[1].isBlank()) {
            throw new MonkeException("You must format your event like this:\n" +
                    "\t\tdeadline <task> /from <start time> /to <end time>");
        }
        String start = tmp2[0];
        String end = tmp2[1];
        return new EventCommand(description, start, end);
    }

    /**
     * Parses a delete command and returns a DeleteCommand object.
     *
     * @param args The arguments of the delete command.
     * @return A DeleteCommand object that can execute the delete command.
     * @throws MonkeException If no argument provided or argument is not a number.
     */
    public static DeleteCommand parseDelete(String args) throws MonkeException {
        if (args.isBlank()) {
            throw new MonkeException("OOGA BOOGA!! Please provide a list number");
        }
        try {
            Integer.parseInt(args);
        } catch (NumberFormatException e) {
            throw new MonkeException("OOGA BOOGA!! Please provide a list number");
        }
        return new DeleteCommand(args);
    }

    public static FindCommand parseFindCommand(String args) throws MonkeException {
        if (args.isBlank()) {
            throw new MonkeException("OOGA BOOGA!! Provide a keyword to search");
        }
        return new FindCommand(args);
    }
}
