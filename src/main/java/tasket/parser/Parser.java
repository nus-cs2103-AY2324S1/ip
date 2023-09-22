package tasket.parser;

import static tasket.commons.Messages.MESSAGE_UNABLE_RETRIEVE_TASK;
import static tasket.commons.Messages.MESSAGE_UNKNOWN_COMMAND;

import tasket.command.AddCommand;
import tasket.command.ByeCommand;
import tasket.command.Command;
import tasket.command.DeleteCommand;
import tasket.command.FindCommand;
import tasket.command.ListCommand;
import tasket.command.MarkCommand;
import tasket.command.UnmarkCommand;
import tasket.exception.TasketException;
import tasket.task.Deadline;
import tasket.task.Event;
import tasket.task.Task;
import tasket.task.ToDo;

/**
 * The class for the parser.
 */
public class Parser {

    private static final int LENGTH_TODO = 3;
    private static final int LENGTH_DEADLINE = 4;
    private static final int LENGTH_EVENT = 5;

    /**
     * Parses the command from user.
     *
     * @param command The full command from user input.
     * @return A command instance to be executed.
     * @throws TasketException If the command does not exists.
     */
    public static Command parseInput(String command) throws TasketException {
        String[] commandParts = command.split(" ", 2);
        switch (commandParts[0]) {
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(commandParts[0], command);
        case "delete":
            return new DeleteCommand(command.replaceFirst("delete", "").trim());
        case "mark":
            return new MarkCommand(command.replaceFirst("mark", "").trim());
        case "unmark":
            return new UnmarkCommand(command.replaceFirst("unmark", "").trim());
        case "list":
            return new ListCommand();
        case "find":
            return new FindCommand(command.replaceFirst("find", "").trim());
        case "bye":
            return new ByeCommand();
        default:
            throw new TasketException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Parses the save format of the task.
     *
     * @param taskSave The save string to be parsed.
     * @return The task instance according to format.
     * @throws TasketException If the string does not follow the respective task formats.
     */
    public static Task parseSaveString(String taskSave) throws TasketException {
        String[] taskElements = taskSave.split(" \\| ");
        Task task = null;

        switch (taskElements[0]) {
        case "T":
            checkLength(taskElements.length, LENGTH_TODO);
            task = new ToDo(taskElements[2]);
            break;
        case "D":
            checkLength(taskElements.length, LENGTH_DEADLINE);
            task = new Deadline(taskElements[2], taskElements[3]);
            break;
        case "E":
            checkLength(taskElements.length, LENGTH_EVENT);
            task = new Event(taskElements[2], taskElements[3], taskElements[4]);
            break;
        default:
            throw new TasketException(MESSAGE_UNABLE_RETRIEVE_TASK);
        }

        assert task != null;
        // Mark tasks as done before adding if it is done in save status.
        if (taskElements[1].equals("1")) {
            task.markAsDone();
        }

        return task;

    }

    /**
     * Checks if task elements in save file fits the desired length.
     *
     * @param taskLen Number of task elements.
     * @param len The desired length.
     * @throws TasketException If the task elements is smaller than desired length.
     */
    private static void checkLength(int taskLen, int len) throws TasketException {
        if (taskLen < len) {
            throw new TasketException(MESSAGE_UNABLE_RETRIEVE_TASK);
        }
    }
}
