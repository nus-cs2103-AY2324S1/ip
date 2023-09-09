package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

/**
 * Clears tasks in tasklist.
 */
public class ClearCommand extends Command {
    /**
     * Constructs the ClearCommand class.
     *
     * @param taskDescription Task description of the task.
     */
    public ClearCommand(String taskDescription) {
        super(taskDescription);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clearTask();
        Storage.save(tasks);
        return ui.showClearMessage();
    }
}
