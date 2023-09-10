package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class UpdateStartCommand extends Command {

    private int position;
    private LocalDate start;

    /**
     * Constructs a new UpdateStartCommand object.
     *
     * @param position Position of the task to update.
     * @param start Updated start date.
     */
    public UpdateStartCommand(int position, LocalDate start) {
        this.position = position;
        this.start = start;
    }

    /**
     * Executes the UpdateStartCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.updateTaskStart(ui, position, start);
    }

    /**
     * Checks if the UpdateStartCommand is an ExitCommand.
     *
     * @return Boolean representing whether the UpdateStartCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
