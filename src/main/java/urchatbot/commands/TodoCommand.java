package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.tasks.Task;
import urchatbot.tasks.ToDo;
import urchatbot.ui.Ui;

/**
 * Adds Todo type of task.
 */
public class TodoCommand extends Command {
    /**
     * Constructs the TodoCommand class.
     *
     * @param taskDescription Task description of the task.
     */
    public TodoCommand(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task newTask = new ToDo(getTaskDescription(), false);
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
