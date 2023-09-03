package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Handles interpretation and execution of user commands.
 */
public class Parser {

    /**
     * Interprets and executes user commands.
     *
     * @param fullCommand Command to be parsed.
     * @param tasks Existing TaskList of tasks.
     * @return Command to execute relevant actions.
     * @throws DukeException If error arises during execution.
     */
    public static Command parse(String fullCommand, TaskList tasks) throws DukeException {

        ArrayList<Task> taskList = tasks.taskList;

        if (fullCommand.equals("bye")) {
            return new ExitCommand();

        } else if (fullCommand.equals("list")) {
            return new ListCommand();

        } else if (fullCommand.contains("unmark")) {
            if (fullCommand.substring(6).isBlank()) {
                throw new DukeException("More information is required to unmark a task");
            } else {
                String[] unparsedTaskIndex = fullCommand.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
                if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                    throw new DukeException("There are only " + taskList.size() + " tasks");
                } else {
                    return new UnmarkCommand(taskIndex);
                }
            }

        } else if (fullCommand.contains("mark")) {
            if (fullCommand.substring(4).isBlank()) {
                throw new DukeException("More information is required to mark a task");
            } else {
                String[] unparsedTaskIndex = fullCommand.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
                if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                    throw new DukeException("There are only " + taskList.size() + " tasks");
                } else {
                    return new MarkCommand(taskIndex);
                }
            }

        } else if (fullCommand.contains("delete")) {
            if (fullCommand.substring(6).isBlank()) {
                throw new DukeException("More information is required to delete a task");
            } else {
                String[] unparsedTaskIndex = fullCommand.split(" ");
                int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
                if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                    throw new DukeException("There are only " + taskList.size() + " tasks");
                } else {
                    return new DeleteCommand(taskIndex);
                }
            }

        } else if (fullCommand.contains("todo")) {
            String taskInfo = fullCommand.substring(4);
            if (taskInfo.isBlank()) {
                throw new DukeException("The description of a todo cannot be empty");
            } else {
                String[] splitCommands = fullCommand.split(" ");
                Task task = new ToDo(splitCommands[1]);
                return new AddCommand(task);
            }

        } else if (fullCommand.contains("deadline")) {
            String taskInfo = fullCommand.substring(8);
            if (taskInfo.isBlank() || !fullCommand.contains("/by")
                    || !fullCommand.contains("deadline ")
                    || fullCommand.substring(fullCommand.indexOf("/by") + 3).isBlank()) {
                throw new DukeException("The description of deadline needs more information");
            } else {
                String[] splitCommands = fullCommand.split(" /by ");
                String[] taskName = splitCommands[0].split(" ");
                Task task = new Deadline(taskName[1], Parser.parseDateTime(splitCommands[1]));
                return new AddCommand(task);
            }

        } else if (fullCommand.contains("event")) {
            String taskInfo = fullCommand.substring(5);
            if (taskInfo.isBlank() || !fullCommand.contains("/from")
                    || !fullCommand.contains("/to") || !fullCommand.contains("event ")
                    || fullCommand.substring(fullCommand.indexOf("/from") + 5).isBlank()
                    || fullCommand.substring(fullCommand.indexOf("/to") + 3).isBlank()) {
                throw new DukeException("The description of event needs more information");
            } else {
                String[] splitCommands = fullCommand.split(" /from ");
                String[] taskName = splitCommands[0].split(" ");
                String[] fromTo = splitCommands[1].split(" /to ");
                Task task = new Event(taskName[1], Parser.parseDateTime(fromTo[0]),
                        Parser.parseDateTime(fromTo[1]));
                return new AddCommand(task);
            }

        } else if (fullCommand.contains("find")) {
            String taskInfo = fullCommand.substring(4);
            if (taskInfo.isBlank()) {
                throw new DukeException("More information is required to find tasks");
            } else {
                return new FindCommand(fullCommand.substring(5));
            }

        } else {
            throw new DukeException("Un-fur-tunately I don't know what you mean :-(");
        }
    }

    /**
     * Parses string input of date into LocalDateTime.
     *
     * @param userInput String representation of date and time.
     * @return LocalDateTime representation of given date and time.
     * @throws DateTimeParseException If error arises during execution.
     */
    public static LocalDateTime parseDateTime(String userInput) throws DateTimeParseException {
            return LocalDateTime.parse(userInput, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }

    /**
     * Formats LocalDateTime into a specified version.
     *
     * @param userInput LocalDateTime with original formatting.
     * @return LocalDateTime with "MMM d yyyy HH:mm" formatting.
     */
    public static String formatDateTime(LocalDateTime userInput) {
        return userInput.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }
}


