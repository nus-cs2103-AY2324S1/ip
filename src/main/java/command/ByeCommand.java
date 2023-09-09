package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command to indicate that the user wishes to exit the chatbot.
 */
public class ByeCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of ByeCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
