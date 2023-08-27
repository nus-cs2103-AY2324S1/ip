package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class MarkDoneCommand extends Command {
    private int index;

    public MarkDoneCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(index);
        ui.printMessage("Beep Boop NICE! I've marked this task as done:\n\t\t" +
                tasks.get(index));
        storage.save(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
