package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException.InvalidToDoException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.tasks.ToDo;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

/**
 * The type To do command.
 */
public class ToDoCommand extends Command {

    /**
     * Instantiates a new To do command.
     *
     * @param args The arguments of the Command.
     */
    public ToDoCommand(String args) {
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
            Task task = new ToDo(Parser.getToDoName(getArgs()));
            taskList.addTask(task);
            return ui.addTask(task, taskList);
        } catch (InvalidToDoException e) {
            return e.getMessage();
        }
    }
}
