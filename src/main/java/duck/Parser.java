
package duck;

import java.util.ArrayList;

import duck.exceptions.DuckException;
import duck.exceptions.FileIoException;
import duck.exceptions.IllegalDateFormatException;
import duck.exceptions.SemanticException;
import duck.exceptions.SyntaxException;
import duck.task.Deadline;
import duck.task.Events;
import duck.task.Task;
import duck.task.TaskList;
import duck.task.ToDo;
import duck.ui.Ui;

/**
 * Deals in connecting with the user command.
 */
public class Parser {
    /**
     * Identifies the type of task.
     * @param command User's inputted text.
     * @return The type of task the user wants to do.
     */
    public String identify(String command) {
        String[] strings = command.split(" ");
        assert strings.length != 0 : "command should not be empty";
        return strings[0];
    }

    /**
     * Parses and executes user command to manage tasks.
     * @param str User's inputted String (Command).
     * @param ui Ui object for input and output
     * @param taskList List containing Tasks.
     * @param storage Acts as a storage object for saving tasks.
     * @return boolean value depicting whether application should run or should be exited.
     */

    public String parse(String str, Ui ui, TaskList taskList, Storage storage) {
        String command = identify(str);
        try {
            return handleCommand(str, command, taskList, ui, storage);
        } catch (DuckException | IllegalDateFormatException e) {
            return "ERROR : " + e.getMessage();
        }
    }

    /**
     * Handles all valid commands, else throws Exception
     * @param str User's inputted String (Command).
     * @param command Command extracted from inputted String
     * @param taskList List containing Tasks.
     * @param ui Ui object for input and output
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws DuckException if command not recognized
     */
    private String handleCommand(String str, String command, TaskList taskList, Ui ui, Storage storage)
            throws DuckException {
        String result;
        switch (command) {
        case "deadline":
            return parseDeadlineCommand(str, taskList, ui, storage);
        case "event":
            return parseEventCommand(str, taskList, ui, storage);
        case "todo":
            return parseTodoCommand(str, taskList, ui, storage);
        case "delete":
            return parseDeleteCommand(str, taskList, storage);
        case "mark":
            return parseMarkCommand(str, taskList, storage);
        case "unmark":
            return parseUnmarkCommand(str, taskList, storage);
        case "list":
            return ui.display(taskList);
        case "bye":
            return ui.exit();
        case "find":
            return parseFindCommand(str, taskList, ui);
        case "view":
            return parseViewCommand(str, taskList, ui);
        default:
            throw new DuckException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses and Process the deadline command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param ui Ui object for input and output
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws SyntaxException for invalid command syntax
     * @throws FileIoException for File IO errors
     */
    private String parseDeadlineCommand(String str, TaskList taskList, Ui ui, Storage storage)
            throws SyntaxException, FileIoException {
        Deadline tempDeadline = taskList.setDeadline(str.substring(8));
        taskList.addTask(tempDeadline);
        String result = ui.echoAdd(tempDeadline, taskList);
        storage.saveInFile(taskList);
        return result;
    }

    /**
     * Parses and Process the event command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param ui Ui object for input and output
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws SyntaxException for invalid command syntax
     * @throws FileIoException for File IO errors
     */
    private String parseEventCommand(String str, TaskList taskList, Ui ui, Storage storage)
            throws SyntaxException, FileIoException {
        Events tempEvent = taskList.setEvent(str.substring(5));
        taskList.addTask(tempEvent);
        String result = ui.echoAdd(tempEvent, taskList);
        storage.saveInFile(taskList);
        return result;
    }

    /**
     * Parses and Process the todo command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param ui Ui object for input and output
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws SyntaxException for invalid command syntax
     * @throws FileIoException for File IO errors
     */
    private String parseTodoCommand(String str, TaskList taskList, Ui ui, Storage storage) throws DuckException {
        ToDo tempToDo = taskList.setToDo(str.substring(4));
        taskList.addTask(tempToDo);
        String result = ui.echoAdd(tempToDo, taskList);
        storage.saveInFile(taskList);
        return result;
    }

    /**
     * Parses and Process the delete command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws SyntaxException for invalid command syntax
     * @throws SemanticException for illogical command arguments
     * @throws FileIoException for File IO errors
     */
    private String parseDeleteCommand(String str, TaskList taskList, Storage storage)
            throws SyntaxException, SemanticException, FileIoException {
        Task tempDelete = taskList.deleteTask(str, taskList);
        storage.saveInFile(taskList);
        StringBuilder deleteResult = new StringBuilder();
        deleteResult.append("Noted. I've removed this task:\n");
        deleteResult.append(" ").append(tempDelete.toString()).append("\n");
        deleteResult.append("Now you have ").append(taskList.size()).append(" tasks in the list.");
        return deleteResult.toString();
    }

    /**
     * Parses and Process the mark command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws SyntaxException for invalid command syntax
     * @throws SemanticException for illogical command arguments
     * @throws FileIoException for File IO errors
     */
    private String parseMarkCommand(String str, TaskList taskList, Storage storage)
            throws SyntaxException, SemanticException, FileIoException {
        Task tempMark = taskList.setDone(str, taskList);
        StringBuilder markResult = new StringBuilder();
        markResult.append("Nice! I've marked this task as done:\n");
        markResult.append("  ").append(tempMark);
        storage.saveInFile(taskList);
        return markResult.toString();
    }

    /**
     * Parses and Process the unmark command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param storage Acts as a storage object for saving tasks.
     * @return String response after parsing
     * @throws SyntaxException for invalid command syntax
     * @throws SemanticException for illogical command arguments
     * @throws FileIoException for File IO errors
     */
    private String parseUnmarkCommand(String str, TaskList taskList, Storage storage)
            throws SyntaxException, SemanticException, FileIoException {
        Task tempUnmark = taskList.setUndone(str, taskList);
        StringBuilder unmarkResult = new StringBuilder();
        unmarkResult.append("I've marked this task as undone:\n");
        unmarkResult.append("  ").append(tempUnmark);
        storage.saveInFile(taskList);
        return unmarkResult.toString();
    }

    /**
     * Parses and Process the find command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param ui Ui object for input and output
     * @return String response after parsing
     */
    private String parseFindCommand(String str, TaskList taskList, Ui ui) throws SyntaxException {
        ArrayList<Task> temp = taskList.find(str.substring(4));
        return ui.displaySearchResults(temp);
    }

    /**
     * Parses and Process the find command
     * @param str User's inputted String (Command).
     * @param taskList List containing Tasks.
     * @param ui Ui object for input and output
     * @return String response after parsing
     */
    private String parseViewCommand(String str, TaskList taskList, Ui ui) throws SyntaxException {
        ArrayList<Task> tempList = taskList.checkSchedule(str.substring(4));
        return ui.displayScheduledTasks(tempList);
    }
}
