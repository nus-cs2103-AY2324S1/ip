package miles.command;

import miles.Ui;
import miles.Storage;
import miles.TaskList;

public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Return task number from user input.
     * 
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
