package duke.parse.command.update;

import java.time.LocalDateTime;

import duke.Duke;

/**
 * Represents a command to update the deadline time of a deadline.
 */
public class UpdateDeadlineCommand extends UpdateCommand {
    private final LocalDateTime newDeadlineTime;

    /**
     * Instantiates a new command to update the deadline time of a deadline.
     * @param index The index of the task in the task list.
     * @param newDeadlineTime The new deadline.
     */
    public UpdateDeadlineCommand(int index, LocalDateTime newDeadlineTime) {
        super(index);
        this.newDeadlineTime = newDeadlineTime;
    }

    /**
     * Executes the command.
     * @param bot The bot to execute this command on.
     * @return true, as this allows the programme to continue.
     */
    @Override
    public boolean execute(Duke bot) {
        bot.updateDeadline(super.index, this.newDeadlineTime);
        return true;
    }
}
