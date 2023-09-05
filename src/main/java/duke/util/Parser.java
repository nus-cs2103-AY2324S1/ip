package duke.util;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * duke.util.Parser class that deals with making sense of the user command.
 *
 * @author Pearlynn
 */

public class Parser {

    /**
     * Parses the user command.
     *
     * @return The result of parsing the user command.
     */
    public static String parse(String command, TaskList taskList) {
        //Duke.getUi().printLine();
        try {
            if (command.equals("bye")) {
                Duke.setExit(true);
                Duke.getStorage().saveData(Duke.getTaskList().getList());
                return Duke.getUi().showExit();
            } else if (command.equals("list")) {
                return Duke.getUi().printList(taskList.getList());
            } else if (command.startsWith("mark")) {
                return taskList.mark(command);
            } else if (command.startsWith("unmark")) {
                return taskList.unmark(command);
            } else if (command.startsWith("todo")) {
                return taskList.todo(command);
            } else if (command.startsWith("deadline")) {
                return taskList.deadline(command);
            } else if (command.startsWith("event")) {
                return taskList.event(command);
            } else if (command.startsWith("delete")) {
                return taskList.delete(command);
            } else if (command.startsWith("find")) {
                return taskList.find(command);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            //Duke.getUi().showError(e.getMessage());
            return e.getMessage();
        }
        //Duke.getUi().printLine();
    }
}
