package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The Command to indicate that the user wishes to delete a specific task from
 * the task list.
 */
public class DeleteCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of DeleteCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public DeleteCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String input = ui.getInput();
        String indexString = input.split(" ")[1];
        int num = Integer.valueOf(indexString);
        String str = ui.printDeleteTask(taskList, num);
        storage.writeTasks(taskList);
        return str;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
