package duke.command;

import duke.exception.DukeTaskNotFoundException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeTaskNotFoundException {
        try {
            ui.showDoneMessage(tasks.getTask(taskIndex).markAsDone());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeTaskNotFoundException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
