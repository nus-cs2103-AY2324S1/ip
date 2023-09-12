package duke.parse.command.update;

import java.time.LocalDateTime;

import duke.Duke;

/**
 * Represents a command to update the start time of an event.
 */
public class UpdateStartTimeCommand extends UpdateCommand {
    private final LocalDateTime newStartTime;

    /**
     * Instantiates a new command to update the start time of an event.
     * @param index The index of the task in the task list.
     * @param newStartTime The new start time of the event.
     */
    public UpdateStartTimeCommand(int index, LocalDateTime newStartTime) {
        super(index);
        this.newStartTime = newStartTime;
    }

    /**
     * Executes the command on the bot.
     * @param bot The bot to execute this command on.
     * @return true, as this allows the programme to continue.
     */
    @Override
    public boolean execute(Duke bot) {
        bot.updateStartTime(super.index, this.newStartTime);
        return true;
    }
}
