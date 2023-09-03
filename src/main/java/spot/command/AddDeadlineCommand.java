package spot.command;

import java.time.LocalDate;

import spot.Storage;
import spot.TaskList;
import spot.Ui;
import spot.exception.SpotException;
import spot.task.Deadline;

/**
 * Represents a command to add a new deadline.
 */
public class AddDeadlineCommand extends Command {

    private String description;
    private LocalDate deadline;

    /**
     * Constructs a new AddDeadlineCommand object.
     *
     * @param description Description of the deadline.
     * @param deadline Due date of the deadline.
     */
    public AddDeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Executes the AddDeadlineCommand.
     *
     * @param tasks Current TaskList.
     * @param ui Current Ui object.
     * @param storage Current Storage object.
     * @throws SpotException If there are any errors when executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SpotException {
        Deadline d = tasks.addDeadline(description, deadline);
        ui.setAdd(tasks, d);
    }

    /**
     * Checks if the AddDeadlineCommand is an ExitCommand.
     *
     * @return Boolean representing whether the AddDeadlineCommand is an ExitCommand.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
