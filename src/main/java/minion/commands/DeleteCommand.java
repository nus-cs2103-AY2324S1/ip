package minion.commands;

import minion.data.exception.IllegalValueException;
import minion.data.TaskList;
import minion.data.task.Task;
import minion.storage.Storage;
import minion.ui.Ui;

import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private final int taskIdx;
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalValueException, IOException {
        Task task = tasks.deleteTask(taskIdx);
        ui.print("OK, I've removed this task",
                "\t" + task.toString()
        );
        storage.writeToFile(tasks);
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof DeleteCommand)) return false;
        DeleteCommand c = (DeleteCommand) o;
        return this.taskIdx == c.taskIdx;
    }
}
