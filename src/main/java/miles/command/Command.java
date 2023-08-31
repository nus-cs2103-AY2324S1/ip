package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;

public class Command {

    /**
     * Executes the command.
     * 
     * @param taskList the task list object
     * @param ui       the ui object
     * @param storage  the storage object
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {};

    /**
     * Returns a task number from either a mark, unmark or delete command.
     * 
     * @param command the command that the user inputs: either "mark", "unmark" or "delete"
     * @param input   user input
     * @return        task number that the user wants to mark, unmark or delete
     */
    public int getTaskNumber(String command, String input) {
        String taskNum = input.replace(command + " ", "");
        String trimmedTaskNum = taskNum.trim();
        return Integer.valueOf(trimmedTaskNum);
    }

    /**
     * Returns true if the command is an exit command else false.
     * 
     * @return true if the command is an exit command else false
     */
    public boolean isExit() {
        return false;
    }
}
