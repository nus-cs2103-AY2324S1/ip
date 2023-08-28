package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String args) {
        super(args);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.deleteTask(Integer.parseInt(getArgs()));
            ui.deleteTask(task, taskList);
        } catch (NumberFormatException | InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
