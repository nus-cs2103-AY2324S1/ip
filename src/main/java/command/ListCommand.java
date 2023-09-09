package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command to indicate that the user wants the chatbot to list out the current
 * tasks in the task list.
 */
public class ListCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of ListCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public ListCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
