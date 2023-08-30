package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Task;
import duke.storage.Storage;
import duke.ui.UI;

import java.io.IOException;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        Task unmarkedTask = taskList.unmarkTask(this.taskNumber);
        storage.write(taskList.getTasks());
        ui.showUnmarkedTask(unmarkedTask);
    }
}