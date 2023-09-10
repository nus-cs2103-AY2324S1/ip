package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class UpdateEndCommand extends Command {

    private int position;
    private LocalDate end;

    /**
     * Constructs a new UpdateEndCommand object.
     *
     * @param position Position of the task to update.
     * @param end Updated end date.
     */
    public UpdateEndCommand(int position, LocalDate end) {
        this.position = position;
        this.end = end;
    }

    /**
     * Executes the UpdateEndCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.updateTaskEnd(ui, position, end);
    }

    /**
     * Checks if the UpdateEndCommand is an ExitCommand.
     *
     * @return Boolean representing whether the UpdateEndCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
