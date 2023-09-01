package duke.parser;

import java.util.regex.Pattern;
import duke.command.Commands;
import duke.dukeException.DukeException;
import duke.taskList.TaskList;

/**
 * deals with making sense of the user duke.command.
 */
public class Parser {
    /**
     * The constructor for the Parser class.
     */
    public Parser() {

    }
    Pattern listRegex = Pattern.compile("^" + Commands.LIST, Pattern.CASE_INSENSITIVE);
    Pattern markRegex = Pattern.compile("^" + Commands.MARK, Pattern.CASE_INSENSITIVE);
    Pattern unmarkRegex = Pattern.compile("^" + Commands.UNMARK, Pattern.CASE_INSENSITIVE);
    Pattern deadlineRegex = Pattern.compile("^" + Commands.DEADLINE, Pattern.CASE_INSENSITIVE);
    Pattern todoRegex = Pattern.compile("^" + Commands.TODO, Pattern.CASE_INSENSITIVE);
    Pattern eventRegex = Pattern.compile("^" + Commands.EVENT, Pattern.CASE_INSENSITIVE);
    Pattern deleteRegex = Pattern.compile("^" + Commands.DELETE, Pattern.CASE_INSENSITIVE);
    Pattern findRegex = Pattern.compile("^" + Commands.FIND, Pattern.CASE_INSENSITIVE);


    /**
     * This will be responsible for parsing user duke.command
     * and the duke.command will be acted out on the TaskList input.
     * @param command User input to be parsed by duke.parser.
     * @param taskList The Tasklist that the duke.command would be used on.
     * @return boolean Returns true if user inputs "bye".
     */
    public boolean parseCommand(String command, TaskList taskList) {
        if (command.equals("bye")) {
            return true; // when returning 0, the application closes
        } else if (listRegex.matcher(command).find()) {
            taskList.printList();
            return false;
        } else if (markRegex.matcher(command).find()) {
            int curr = Integer.parseInt(command.substring(5)) - 1;
            taskList.markCurrentTaskDone(curr);
            return false;
        } else if (unmarkRegex.matcher(command).find()) {
            int curr = Integer.parseInt(command.substring(7)) - 1;
            taskList.markCurrentTaskUndone(curr);
            return false;
        } else if (deadlineRegex.matcher(command).find()) {
            taskList.addDeadline(command);
            return false;
        } else if (todoRegex.matcher(command).find()) {
            taskList.addTodo(command);
            return false;
        } else if (eventRegex.matcher(command).find()) {
            taskList.addEvent(command);
            return false;
        } else if (deleteRegex.matcher(command).find()) {
            taskList.deleteTask(command);
            return false;
        } else if (findRegex.matcher(command).find()) {
            taskList.findTasks(command);
            return false;
        } else {
            try {
                throw new DukeException("Invalid Response");
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                return false;
            }
        }
    }
}
