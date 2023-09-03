package command;

import duke.Ui;
import duke.TaskList;

/**
 * The Command to indicate that the user wishes to exit the chatbot.
 */
public class ByeCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of ByeCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public ByeCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
