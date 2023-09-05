package duke.parser;

import java.util.regex.Pattern;

import duke.command.Commands;
import duke.dukeException.DukeException;
import duke.taskList.TaskList;

/**
 * deals with making sense of the user duke.command.
 */
public class Parser {

    private Pattern listRegex = Pattern.compile("^" + Commands.LIST, Pattern.CASE_INSENSITIVE);
    private Pattern markRegex = Pattern.compile("^" + Commands.MARK, Pattern.CASE_INSENSITIVE);
    private Pattern unmarkRegex = Pattern.compile("^" + Commands.UNMARK, Pattern.CASE_INSENSITIVE);
    private Pattern deadlineRegex = Pattern.compile("^" + Commands.DEADLINE, Pattern.CASE_INSENSITIVE);
    private Pattern todoRegex = Pattern.compile("^" + Commands.TODO, Pattern.CASE_INSENSITIVE);
    private Pattern eventRegex = Pattern.compile("^" + Commands.EVENT, Pattern.CASE_INSENSITIVE);
    private Pattern deleteRegex = Pattern.compile("^" + Commands.DELETE, Pattern.CASE_INSENSITIVE);
    private Pattern findRegex = Pattern.compile("^" + Commands.FIND, Pattern.CASE_INSENSITIVE);
    /**
     * The constructor for the Parser class.
     */
    public Parser() {

    }

    /**
     * This will be responsible for parsing user duke.command
     * and the duke.command will be acted out on the TaskList input.
     * @param command User input to be parsed by duke.parser.
     * @param taskList The Tasklist that the duke.command would be used on.
     * @return boolean Returns true if user inputs "bye".
     */
    public String parseCommand(String command, TaskList taskList) {
        if (command.equals("bye")) {
            return "bye"; // when returning 0, the application closes
        } else if (listRegex.matcher(command).find()) {
            return taskList.printList();
        } else if (markRegex.matcher(command).find()) {
            int curr = Integer.parseInt(command.substring(5)) - 1;
            return taskList.markCurrentTaskDone(curr);
        } else if (unmarkRegex.matcher(command).find()) {
            int curr = Integer.parseInt(command.substring(7)) - 1;
            return taskList.markCurrentTaskUndone(curr);
        } else if (deadlineRegex.matcher(command).find()) {
            return taskList.addDeadline(command);
        } else if (todoRegex.matcher(command).find()) {
            return taskList.addTodo(command);
        } else if (eventRegex.matcher(command).find()) {
            return taskList.addEvent(command);
        } else if (deleteRegex.matcher(command).find()) {
            return taskList.deleteTask(command);
        } else if (findRegex.matcher(command).find()) {
            return taskList.findTasks(command);
        } else {
            try {
                throw new DukeException("Invalid Response");
            } catch (DukeException e) {
                return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
            }
        }
    }

    public Pattern getListRegex() {
        return this.listRegex;
    }

    public Pattern getFindRegex() {
        return this.findRegex;
    }
}
