package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.commands.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


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

        } else if (fullCommand.equals("clear")) {
            return new ClearCommand();

        } else if (fullCommand.equals("list")) {
            return new ListCommand();

        } else if (fullCommand.contains("unmark")) {
            return handleUnmark(fullCommand, taskList);

        } else if (fullCommand.contains("mark")) {
            return handleMark(fullCommand, taskList);

        } else if (fullCommand.contains("delete")) {
            return handleDelete(fullCommand, taskList);

        } else if (fullCommand.contains("todo")) {
            return handleTodo(fullCommand, taskList);

        } else if (fullCommand.contains("deadline")) {
            return handleDeadline(fullCommand, taskList);

        } else if (fullCommand.contains("event")) {
            return handleEvent(fullCommand, taskList);

        } else if (fullCommand.contains("find")) {
            return handleFind(fullCommand, taskList);

        } else if (fullCommand.contains("update")) {
            return handleUpdate(fullCommand, taskList);

        } else {
            throw new DukeException("Un-fur-tunately I don't know what you mean :-(");
        }
    }

    private static Command handleUnmark(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        if (fullCommand.substring(6).isBlank()) {
            throw new DukeException("More information is required to unmark a task");
        } else {
            String[] unparsedTaskIndex = fullCommand.split(" ");
            assert !unparsedTaskIndex[1].isBlank() : "Index is empty";
            int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
            if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                throw new DukeException("There are only " + taskList.size() + " tasks");
            } else {
                return new UnmarkCommand(taskIndex);

            }
        }
    }

    private static Command handleMark(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        if (fullCommand.substring(4).isBlank()) {
            throw new DukeException("More information is required to mark a task");
        } else {
            String[] unparsedTaskIndex = fullCommand.split(" ");
            assert !unparsedTaskIndex[1].isBlank() : "Index is empty";
            int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
            if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                throw new DukeException("There are only " + taskList.size() + " tasks");
            } else {
                return new MarkCommand(taskIndex);

            }
        }
    }

    private static Command handleDelete(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        if (fullCommand.substring(6).isBlank()) {
            throw new DukeException("More information is required to delete a task");
        } else {
            String[] unparsedTaskIndex = fullCommand.split(" ");
            assert !unparsedTaskIndex[1].isBlank() : "Index is empty";
            int taskIndex = Integer.parseInt(unparsedTaskIndex[1]) - 1;
            if (taskIndex >= (taskList.size()) || taskIndex < 0) {
                throw new DukeException("There are only " + taskList.size() + " tasks");
            } else {
                return new DeleteCommand(taskIndex);
            }
        }
    }

    private static Command handleTodo(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        String taskInfo = fullCommand.substring(4);
        if (taskInfo.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty");
        } else {
            Task task = new ToDo(taskInfo);
            return new AddCommand(task);
        }
    }

    private static Command handleDeadline(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        String taskInfo = fullCommand.substring(8);
        if (taskInfo.isBlank() || !fullCommand.contains("/by")
                || !fullCommand.contains("deadline ")
                || fullCommand.substring(fullCommand.indexOf("/by") + 3).isBlank()) {
            throw new DukeException("The description of deadline needs more information");
        } else {
            String[] splitCommands = taskInfo.split(" /by ");
            String taskName = splitCommands[0].trim();
            assert !taskName.isBlank() : "Description is empty";
            assert !splitCommands[1].isBlank() : "'by' is empty";
            Task task = new Deadline(taskName, Parser.parseDateTime(splitCommands[1]));
            return new AddCommand(task);
        }
    }

    private static Command handleEvent(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        String taskInfo = fullCommand.substring(5);
        if (taskInfo.isBlank() || !fullCommand.contains("/from")
                || !fullCommand.contains("/to")
                || !fullCommand.contains("event ")
                || fullCommand.substring(fullCommand.indexOf("/from") + 5).isBlank()
                || fullCommand.substring(fullCommand.indexOf("/to") + 3).isBlank()) {
            throw new DukeException("The description of event needs more information");
        } else {
            String[] splitCommands = taskInfo.split(" /from ");
            String taskName = splitCommands[0].trim();
            String[] fromTo = splitCommands[1].split(" /to ");
            assert !taskName.isBlank() : "Description is empty";
            assert !fromTo[0].isBlank() : "'from' is empty";
            assert !fromTo[1].isBlank() : "'to' is empty";
            Task task = new Event(taskName, Parser.parseDateTime(fromTo[0]),
                    Parser.parseDateTime(fromTo[1]));
            return new AddCommand(task);
        }
    }

    private static Command handleFind(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        String taskInfo = fullCommand.substring(4);
        if (taskInfo.isBlank()) {
            throw new DukeException("More information is required to find tasks");
        } else {
            return new FindCommand(fullCommand.substring(5));
        }
    }

    private static Command handleUpdate(String fullCommand, ArrayList<Task> taskList) throws DukeException {
        String taskInfo = fullCommand.substring(6);

        if (taskInfo.isBlank()) {
            throw new DukeException("More information is needed to update a task.");

        } else {
            String[] unparsedInfoArray = taskInfo.split("/");
            int taskIndex = Integer.parseInt(unparsedInfoArray[0].trim()) - 1;
            Task task = taskList.get(taskIndex);

            if (task instanceof ToDo) {
                return handleToDoUpdate(taskIndex, unparsedInfoArray);

            } else if (task instanceof Deadline) {
                return handleDeadlineUpdate(taskIndex, unparsedInfoArray);

            } else if (task instanceof Event) {
                return handleEventUpdate(taskIndex, unparsedInfoArray);

            } else {
                throw new DukeException("Incorrect information in input.");
            }
        }
    }

    private static Command handleToDoUpdate(int taskIndex, String[] unparsedInfoArray) throws DukeException {
        String[] unparsedFieldUpdate = unparsedInfoArray[1].split("is ");
        String fieldToUpdate = unparsedFieldUpdate[0].trim();
        String newDescription = unparsedFieldUpdate[1].trim();
        if (fieldToUpdate.equals("description")) {
            return new UpdateDescriptionCommand(taskIndex, newDescription);
        } else {
            throw new DukeException("Field to be updated is incorrect.");
        }
    }

    private static Command handleDeadlineUpdate(int taskIndex, String[] unparsedInfoArray) throws DukeException {
        String fieldToUpdate = "";
        String[] unparsedFieldUpdate = unparsedInfoArray[1].split("is ");
        String update = unparsedFieldUpdate[1].trim();
        if (unparsedFieldUpdate[0].trim().equals("description") && !update.isBlank()) {
            fieldToUpdate = "description";
            return new UpdateDescriptionCommand(taskIndex, update);
        } else if (unparsedFieldUpdate[0].trim().equals("by") && !update.isBlank()) {
            fieldToUpdate = "by";
            LocalDateTime newDate = parseDateTime(update);
            return new UpdateDateCommand(taskIndex, fieldToUpdate, newDate);

        } else {
            throw new DukeException("Field to be updated is incorrect.");
        }
    }

    private static Command handleEventUpdate(int taskIndex, String[] unparsedInfoArray) throws DukeException {
        String fieldToUpdate = "";
        String[] unparsedFieldUpdate = unparsedInfoArray[1].split("is ");
        String update = unparsedFieldUpdate[1].trim();
        if (unparsedFieldUpdate[0].trim().equals("description") && !update.isBlank()) {
            fieldToUpdate = "description";
            return new UpdateDescriptionCommand(taskIndex, update);

        } else if (unparsedFieldUpdate[0].trim().equals("from") && !update.isBlank()) {
            fieldToUpdate = "from";
            LocalDateTime newDate = parseDateTime(update);
            return new UpdateDateCommand(taskIndex, fieldToUpdate, newDate);

        } else if (unparsedFieldUpdate[0].trim().equals("to") && !update.isBlank()) {
            fieldToUpdate = "to";
            LocalDateTime newDate = parseDateTime(update);
            return new UpdateDateCommand(taskIndex, fieldToUpdate, newDate);
        } else {
            throw new DukeException("Field to be updated is incorrect.");
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




