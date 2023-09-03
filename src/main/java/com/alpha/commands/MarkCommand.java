package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

/**
 * The type Mark command.
 */
public class MarkCommand extends Command {

    /**
     * Instantiates a new Mark command.
     *
     * @param args The arguments of the Command.
     */
    public MarkCommand(String args) {
        super(args);
    }

    /**
     * Executes the commands.
     *
     * @param taskList   Task list of the application.
     * @param ui      Ui of the application.
     * @param storage Storage functionality of the application.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.markTask(Integer.parseInt(getArgs()));
            return ui.markTask(task);
        } catch (NumberFormatException | InvalidTaskException e) {
            return e.getMessage();
        }
    }
}
