package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddDeadlineCommand extends Command {
    private final String description;
    private final String by;

    public AddDeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (description.isEmpty() || by.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The format of a deadline should be: deadline DESCRIPTION /by DATE");
        }
        tasks.addTask(new Deadline(description, by));
        ui.showTaskAddedMessage(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
