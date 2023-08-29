/**
 * Parser class that deals with making sense of the user command.
 *
 * @author Pearlynn
 */

public class Parser {

    /**
     * Parses the user command.
     */
    public static void parse(String command, TaskList taskList) {
        Duke.ui.printLine();
        try {
            if (command.equals("bye")) {
                Duke.isExit = true;
                Duke.storage.saveData(Duke.taskList.getList());
                Duke.ui.showExit();
            } else if (command.equals("list")) {
                Duke.ui.printList(taskList.getList());
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
            Duke.ui.showError(e.getMessage());
        }
        Duke.ui.printLine();
    }
}
