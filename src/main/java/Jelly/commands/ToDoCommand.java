package Jelly.commands;

import Jelly.main.Storage;
import Jelly.main.TaskList;
import Jelly.main.Ui;

import Jelly.task.Todo;

public class ToDoCommand extends Command {
    private String description;

    public ToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        Todo todoTask = new Todo(description);
        taskList.add(todoTask);
        storage.saveAndExit(taskList);
        return ui.addedTaskMessage(todoTask, taskList.size());
    }
}
