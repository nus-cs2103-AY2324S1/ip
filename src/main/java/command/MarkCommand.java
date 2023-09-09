package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command that indicates the user wishes to mark a specific task as done.
 */
public class MarkCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of MarkCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public MarkCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        String str = ui.printMarkedTask(taskList, num);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
