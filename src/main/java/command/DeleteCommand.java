package command;

import duke.Ui;
import duke.TaskList;

/**
 * The Command to indicate that the user wishes to delete a specific task from
 * the task list.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of DeleteCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public DeleteCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        taskList.deleteTask(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}