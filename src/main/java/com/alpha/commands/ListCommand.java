package com.alpha.commands;

import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

/**
 * The type List command.
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new List command.
     */
    public ListCommand() {
        super();
    }

    /**
     * Executes the commands.
     *
     * @param taskList   Task list of the application.
     * @param ui      Ui of the application.
     * @param storage Storage functionality of the application.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.displayTasks(taskList.getTasks());
    }
}
