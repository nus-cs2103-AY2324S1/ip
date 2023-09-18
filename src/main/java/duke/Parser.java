package duke;

import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The Parser class is responsible for parsing user input and converting data strings
 * into task objects for the Duke application.
 */
public class Parser {

    /**
     * Parses the user input command and returns the corresponding Command object.
     *
     * @param strCommand The user input command to be parsed.
     * @return A Command object representing the parsed command.
     * @throws DukeException If the input command is invalid or contains errors.
     */
    public static Command parse(String strCommand) throws DukeException {
        assert strCommand != null : "Command should not be null";
        int firstSpaceIndex = strCommand.indexOf(" ");
        String commandType = firstSpaceIndex != -1
                ? strCommand.substring(0, firstSpaceIndex)
                : strCommand;
        switch (commandType) {
        case "deadline":
            return parseAddDeadlineCommand(strCommand);
        case "event":
            return parseAddEventCommand(strCommand);
        case "todo":
            return parseAddTodoCommand(strCommand);
        case "bye":
            return parseByeCommand(strCommand);
        case "delete":
            return parseDeleteCommand(strCommand);
        case "find":
            return parseFindCommand(strCommand);
        case "list":
            return parseListCommand(strCommand);
        case "mark":
            return parseMarkCommand(strCommand);
        case "unmark":
            return parseUnmarkCommand(strCommand);
        case "update":
            return parseUpdateCommand(strCommand);
        default:
            throw new DukeException("OOPS!!! This command is invalid.");
        }
    }

    private static AddCommand parseAddDeadlineCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        int byIndex = strCommand.indexOf("/by");
        String exception = "OOPS!!! The format of the deadline command is invalid.\n"
                + "Here is an example of a valid format:"
                + " deadline coding /by 2023-09-04";
        if (byIndex == -1 || firstSpaceIndex == -1) {
            throw new DukeException(exception);
        }
        String deadlineDesc = strCommand.substring(firstSpaceIndex + 1, byIndex - 1);
        String by = strCommand.substring(byIndex + "/by ".length());
        if (deadlineDesc.isBlank() || by.isBlank()) {
            throw new DukeException(exception);
        }
        commandDetailList.add(deadlineDesc);
        commandDetailList.add(by);
        return new AddCommand(commandDetailList, "D");
    }

    private static AddCommand parseAddEventCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        int fromIndex = strCommand.indexOf("/from");
        int toIndex = strCommand.indexOf("/to");
        String exception = "OOPS!!! The format of the event command is invalid.\n"
                + "Here is an example of a valid format:"
                + " event coding /from 2023-01-01 /to 2023-12-31";
        if (firstSpaceIndex == -1 || fromIndex == -1 || toIndex == -1 || toIndex < fromIndex) {
            throw new DukeException(exception);
        }
        String eventDesc = strCommand.substring(firstSpaceIndex + 1, fromIndex - 1);
        String from = strCommand.substring(fromIndex + "/from ".length(), toIndex - 1);
        String to = strCommand.substring(toIndex + "/to ".length());
        if (eventDesc.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DukeException(exception);
        }
        commandDetailList.add(eventDesc);
        commandDetailList.add(from);
        commandDetailList.add(to);
        return new AddCommand(commandDetailList, "E");
    }

    private static AddCommand parseAddTodoCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        if (firstSpaceIndex == -1 || strCommand.length() < 6) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String todoDesc = strCommand.substring(firstSpaceIndex + 1);
        ArrayList<String> commandDetailList = new ArrayList<>();
        commandDetailList.add(todoDesc);
        return new AddCommand(commandDetailList, "T");
    }

    private static ByeCommand parseByeCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        commandDetailList.add(strCommand);
        return new ByeCommand(commandDetailList);
    }

    private static DeleteCommand parseDeleteCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        if (firstSpaceIndex == -1 || strCommand.length() < "delete ".length()) {
            throw new DukeException("OOPS!!! The task number to delete cannot be empty.");
        }
        String taskToDelete = strCommand.substring(firstSpaceIndex + 1);
        if (taskToDelete.isBlank()) {
            throw new DukeException("OOPS!!! The task number to delete cannot be empty.");
        }
        commandDetailList.add(taskToDelete);
        return new DeleteCommand(commandDetailList);
    }

    private static FindCommand parseFindCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        if (firstSpaceIndex == -1 || strCommand.length() < "find ".length()) {
            throw new DukeException("OOPS!!! The find keyword cannot be empty.");
        }
        String keyword = strCommand.substring(firstSpaceIndex + 1);
        if (keyword.isBlank()) {
            throw new DukeException("OOPS!!! The find keyword cannot be empty.");
        }
        commandDetailList.add(keyword);
        return new FindCommand(commandDetailList);
    }

    private static ListCommand parseListCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        commandDetailList.add(strCommand);
        return new ListCommand(commandDetailList);
    }

    private static MarkCommand parseMarkCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        if (firstSpaceIndex == -1 || strCommand.length() < "mark ".length()) {
            throw new DukeException("OOPS!!! The task number to mark cannot be empty.");
        }
        String taskToMark = strCommand.substring(firstSpaceIndex + 1);
        if (taskToMark.isBlank()) {
            throw new DukeException("OOPS!!! The task number to mark cannot be empty.");
        }
        commandDetailList.add(taskToMark);
        return new MarkCommand(commandDetailList);
    }

    private static UnmarkCommand parseUnmarkCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        if (firstSpaceIndex == -1 || strCommand.length() < "unmark ".length()) {
            throw new DukeException("OOPS!!! The task number to unmark cannot be empty.");
        }
        String taskToUnmark = strCommand.substring(firstSpaceIndex + 1);
        if (taskToUnmark.isBlank()) {
            throw new DukeException("OOPS!!! The task number to unmark cannot be empty.");
        }
        commandDetailList.add(taskToUnmark);
        return new UnmarkCommand(commandDetailList);
    }

    private static UpdateCommand parseUpdateCommand(String strCommand) throws DukeException {
        int firstSpaceIndex = strCommand.indexOf(" ");
        ArrayList<String> commandDetailList = new ArrayList<>();
        int secondSpaceIndex = strCommand.indexOf(" ", firstSpaceIndex + 1);
        int slashIndex = strCommand.indexOf("/", secondSpaceIndex);
        int thirdSpaceIndex = strCommand.indexOf(" ", slashIndex);
        if (firstSpaceIndex == -1 || strCommand.length() < "update ".length()) {
            throw new DukeException("OOPS!!! The update details cannot be empty.");
        } else if (slashIndex == -1 || secondSpaceIndex == -1) {
            throw new DukeException("OOPS!!! Please use the format: update 1 /desc newName");
        }
        String taskNumber = strCommand.substring(firstSpaceIndex + 1, secondSpaceIndex);
        String field = strCommand.substring(slashIndex + 1, thirdSpaceIndex);
        String details = strCommand.substring(thirdSpaceIndex + 1);
        commandDetailList.add(taskNumber);
        commandDetailList.add(field);
        commandDetailList.add(details);
        System.out.println(field);
        return new UpdateCommand(commandDetailList);
    }
    /**
     * Converts a data string into a Task object.
     *
     * @param data The data string representing a Task.
     * @return A Task object created from the data string.
     * @throws DukeException If there are errors in the data string, or it is in an invalid format.
     */
    public static Task dataToTask(String data) throws DukeException {
        String taskType = data.substring(0, 1);
        String taskData = data.substring(4);

        switch (taskType) {
        case "T":
            return Todo.constructWithData(taskData);
        case "D":
            return Deadline.constructWithData(taskData);
        case "E":
            return Event.constructWithData(taskData);
        default:
            throw new DukeException("Wrong task type.");
        }
    }
}
