package duke.command;

import duke.exception.InvalidTaskNumberException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import java.io.IOException;

public class RemindCommand extends Command {
    private int index;

    /**
     * Constructs a Command object with the specified task.
     *
     * @param index The task associated with the command.
     */
    public RemindCommand(int index) {
        super(null);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (index >= tasks.getSize() || index < 0) throw new InvalidTaskNumberException();
        Task task = tasks.getTaskAtIndex(index);
        task.setReminder();
        storage.update(tasks.getTasks());
    }
}
