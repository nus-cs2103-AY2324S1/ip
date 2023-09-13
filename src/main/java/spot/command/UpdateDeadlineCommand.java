package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;

public class UpdateDeadlineCommand extends Command {

    private int position;
    private LocalDate deadline;

    /**
     * Constructs a new UpdateDeadlineCommand object.
     *
     * @param position Position of the task to update.
     * @param deadline Updated task deadline.
     */
    public UpdateDeadlineCommand(int position, LocalDate deadline) {
        this.position = position;
        this.deadline = deadline;
    }

    /**
     * Executes the UpdateDeadlineCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        tasks.updateTaskDeadline(ui, position, deadline);
    }

    /**
     * Checks if the UpdateDeadlineCommand is an ExitCommand.
     *
     * @return Boolean representing whether the UpdateDeadlineCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
