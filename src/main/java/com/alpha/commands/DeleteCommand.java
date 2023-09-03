package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

/**
 * The type Delete command.
 */
public class DeleteCommand extends Command {

    /**
     * Instantiates a new Delete command.
     *
     * @param args The arguments of the Command.
     */
    public DeleteCommand(String args) {
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
            Task task = taskList.deleteTask(Integer.parseInt(getArgs()));
            return ui.deleteTask(task, taskList);
        } catch (NumberFormatException | InvalidTaskException e) {
            return e.getMessage();
        }
    }
}
