package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command to indicate that the user wishes to exit the chatbot.
 */
public class GreetCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of GreetCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public GreetCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printWelcome();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
