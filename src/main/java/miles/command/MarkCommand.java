package miles.command;

import miles.Storage;
import miles.TaskList;
import miles.Ui;

/**
 * Represents a mark task as done command.
 */
public class MarkCommand extends Command {
    private String input;

    /**
     * Constructor to create a new mark command.
     * @param input user input
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Returns task number from user input.
     * @param input user input
     * @return      task number
     */
    public int getTaskNumber(String input) {
        return super.getTaskNumber("mark", input);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        int taskNum = this.getTaskNumber(this.input);
        taskList.markTaskAsDone(taskNum);
        storage.saveFile(taskList);
    }
}
