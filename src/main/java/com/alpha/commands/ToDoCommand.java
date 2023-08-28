package com.alpha.commands;

import com.alpha.exceptions.InvalidTaskException.InvalidToDoException;
import com.alpha.storage.Storage;
import com.alpha.tasks.Task;
import com.alpha.tasks.TaskList;
import com.alpha.tasks.ToDo;
import com.alpha.ui.Ui;
import com.alpha.utils.Parser;

public class ToDoCommand extends Command {

    public ToDoCommand(String args) {
        super(args);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = new ToDo(Parser.getToDoName(getArgs()));
            taskList.addTask(task);
            ui.addTask(task, taskList);
        } catch (InvalidToDoException e) {
            System.out.println(e.getMessage());
        }
    }
}
