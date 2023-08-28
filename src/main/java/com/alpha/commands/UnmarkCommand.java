package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

public class UnmarkCommand extends Command {

    public UnmarkCommand(String args) {
        super(args);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.unmarkTask(Integer.parseInt(getArgs()));
            ui.unmarkTask(task);
        } catch (NumberFormatException | InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
