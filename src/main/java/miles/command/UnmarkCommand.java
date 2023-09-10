package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;

public class UnmarkCommand extends Command {
    private String input;

    /**
     * Constructor to create a new unmark command.
     * @param input user input
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Returns task number from user input.
     * 
     * @param input user input
     * @return      task number 
     */
    public int getTaskNumber(String input) {
        return super.getTaskNumber("unmark", input);
    }

    @Override 
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskNum = getTaskNumber(input);
        taskList.markTaskAsUndone(taskNum);
        storage.saveFile(taskList);
    }
}
