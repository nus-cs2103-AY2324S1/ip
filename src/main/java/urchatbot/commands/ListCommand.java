package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

/**
 * Lists all tasks in the tasklist to the user.
 */
public class ListCommand extends Command {
    /**
     * Constructs the ListCommand class.
     *
     * @param taskDescription Task description of the task.
     */
    public ListCommand(String taskDescription) {
        super(taskDescription);
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showListMessage(tasks);
    }

}
