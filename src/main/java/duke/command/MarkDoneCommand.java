package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * The MarkDoneCommand is for the "mark" command.
 */
public class MarkDoneCommand extends Command {
    private int index;

    /**
     * The constructor for a MarkDoneCommand.
     *
     * @param index The one-based index of the task to be marked.
     */
    public MarkDoneCommand(int index) {
        this.index = index - 1;
    }

    /**
     * This method is used to execute the mark done command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.markTask(index);
        ui.printMessage("Beep Boop NICE! I've marked this task as done:\n\t\t" + tasks.get(index));
        storage.save(tasks.getAllTasks());
    }

    /**
     * This method is used to check whether it is an exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
