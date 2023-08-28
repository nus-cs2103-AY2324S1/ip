package duke.command;

import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private int taskIndex;

    public UnmarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.unmark(taskIndex);

        storage.save(tasks);
        ui.showMessage(
                "Nice! I've marked this task as not done yet:",
                "\t " + task
        );
    }
}
