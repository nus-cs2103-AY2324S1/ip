package arona.commands;

import arona.storage.Storage;

import arona.task.TaskList;
import arona.task.ToDoTask;
import arona.ui.Ui;

public class ToDoCommand extends Command {
    private Storage storage;
    private ToDoTask todoTask;

    public ToDoCommand(TaskList taskList, Ui ui, Storage storage, String description) {
        super(taskList, ui);
        this.storage = storage;
        this.todoTask = new ToDoTask(description);
    }

    @Override
    public void execute() {
        taskList.getTasks().add(todoTask);
        storage.saveTask(todoTask);
        ui.showTaskAdded(todoTask, taskList.getTasks().size());
    }
}
