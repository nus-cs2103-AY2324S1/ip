package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.tasks.ToDo;
import urchatbot.ui.Ui;

public class TodoCommand extends Command{
    public TodoCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(taskDescription, false);
        tasks.addTask(newTask);
        Storage.save(tasks);
        int taskSize = tasks.getSize();
        if (taskSize == 1 || taskSize == 0) {
            ui.showTodoMessage(newTask.toString(), taskSize);
        } else {
            ui.showTodoMessagePlural(newTask.toString(), taskSize);
        }
    }
}
