package spot.command;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class UpdateDescriptionCommand extends Command {

    private int position;
    private String description;

    /**
     * Constructs a new UpdateDescriptionCommand object.
     *
     * @param position Position of the task to update.
     * @param description Updated task description.
     */
    public UpdateDescriptionCommand(int position, String description) {
        assert !description.isEmpty();
        this.position = position;
        this.description = description;
    }

    /**
     * Executes the UpdateDescriptionCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.updateTaskDescription(ui, position, description);
    }

    /**
     * Checks if the UpdateDescriptionCommand is an ExitCommand.
     *
     * @return Boolean representing whether the UpdateDescriptionCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
