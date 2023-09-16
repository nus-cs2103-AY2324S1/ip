package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Finds a task in the list matching a given description.
 */
public class FindCommand extends Command {
    private String taskToFind;

    /**
     * Creates a FindCommand that finds a certain task based on the specified input.
     * @param taskToFind The user input with the details of the task to be found.
     */
    public FindCommand(String taskToFind) {
        super();
        this.taskToFind = taskToFind;
    }

    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.find(this.taskToFind);
    }
}
