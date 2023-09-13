package duke.command;
import java.io.IOException;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class AddTagCommand extends Command {
    private final int taskIndex;
    private final String tag;

    public AddTagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new DukeException("Invalid task number. Please provide a valid task number.");
        }

        Task task = tasks.getTask(taskIndex);
        task.setTag(tag);
        storage.save(tasks);
        return ui.showTaggedTask(task);
    }
}
