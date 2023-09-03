package command;

import duke.Ui;
import duke.TaskList;

/**
 * The Command that indicates the user wishes to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of MarkCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public MarkCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String input = ui.getInput();
        String indexStr = input.split(" ")[1];
        int num = Integer.valueOf(indexStr);
        taskList.markTask(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
