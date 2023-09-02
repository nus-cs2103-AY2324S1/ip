package arona.commands;

import arona.storage.Storage;

import arona.task.TaskList;
import arona.task.TodoTask;
import arona.ui.Ui;

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
