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
     */
    public static void parse(String command, TaskList taskList) {
        Duke.getUi().printLine();
        try {
            if (command.equals("bye")) {
                Duke.setExit(true);
                Duke.getStorage().saveData(Duke.getTaskList().getList());
                Duke.getUi().showExit();
            } else if (command.equals("list")) {
                Duke.getUi().printList(taskList.getList());
            } else if (command.startsWith("mark")) {
                taskList.mark(command);
            } else if (command.startsWith("unmark")) {
                taskList.unmark(command);
            } else if (command.startsWith("todo")) {
                taskList.todo(command);
            } else if (command.startsWith("deadline")) {
                taskList.deadline(command);
            } else if (command.startsWith("event")) {
                taskList.event(command);
            } else if (command.startsWith("delete")) {
                taskList.delete(command);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            Duke.getUi().showError(e.getMessage());
        }
        Duke.getUi().printLine();
    }
}
