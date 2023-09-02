package commands;

import storage.Storage;

import task.TaskList;
import task.TodoTask;
import ui.Ui;

public class ToDoCommand extends Command {
    private Storage storage;
    private TodoTask todoTask;

    public ToDoCommand(TaskList taskList, Ui ui, Storage storage, String description) {
        super(taskList, ui);
        this.storage = storage;
        this.todoTask = new TodoTask(description);
    }

    @Override
    public void execute() {
        taskList.getTasks().add(todoTask);
        storage.saveTask(todoTask);
        ui.showTaskAdded(todoTask, taskList.getTasks().size());
    }
}
