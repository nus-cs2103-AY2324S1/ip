package commands;

import data.TaskList;
import data.exception.DukeException;
import data.task.Task;
import storage.Storage;
import ui.UI;

import java.io.IOException;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private final int taskNumber;
    public MarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        Task markedTask = taskList.markTask(taskNumber);
        storage.write(taskList.getTasks());
        ui.showMarkedTask(markedTask);
    }
}
