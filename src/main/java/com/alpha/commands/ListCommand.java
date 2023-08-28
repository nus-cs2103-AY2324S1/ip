package com.alpha.commands;

import com.alpha.storage.Storage;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayTasks(taskList.getTasks());
    }
}
