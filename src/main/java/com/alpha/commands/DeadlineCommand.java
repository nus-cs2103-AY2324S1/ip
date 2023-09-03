package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Deadline;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

/**
 * The type Deadline command.
 */
public class DeadlineCommand extends Command {

    /**
     * Instantiates a new Deadline command.
     *
     * @param args The arguments of the Command.
     */
    public DeadlineCommand(String args) {
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
            Task task = new Deadline(Parser.getDeadlineName(getArgs()), Parser.getDeadlineEnd(getArgs()));
            taskList.addTask(task);
            return ui.addTask(task, taskList);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
    }
}
