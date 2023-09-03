package command;

import duke.Ui;
import duke.TaskList;

/**
 * The Command which indicates the user wishes to mark a specific task in the
 * task list as incomplete.
 */
public class UnmarkCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of UnmarkCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public UnmarkCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        taskList.unmarkTask(num);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
