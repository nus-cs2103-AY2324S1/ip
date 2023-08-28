package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Deadline;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

public class DeadlineCommand extends Command {

    public DeadlineCommand(String args) {
        super(args);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = new Deadline(Parser.getDeadlineName(getArgs()), Parser.getDeadlineEnd(getArgs()));
            taskList.addTask(task);
            ui.addTask(task, taskList);
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
