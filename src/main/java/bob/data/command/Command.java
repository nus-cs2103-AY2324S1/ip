package bob.data.command;

import bob.data.task.Task;
import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

import java.util.ArrayList;

public abstract class Command {
    private String taskDescription;

    public Command(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public abstract void execute(Storage storage, TaskList list, Ui ui);
}
