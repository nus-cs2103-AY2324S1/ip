package duke.parse.command.update;

import java.time.LocalDateTime;

import duke.Duke;

/**
 * Represents a command to update the end time of an event.
 */
public class UpdateEndTimeCommand extends UpdateCommand {
    private final LocalDateTime newEndTime;

    /**
     * Instantiates a new command to update the end time of an event.
     * @param index The index of the event in the task list.
     * @param newEndTime The new end time of the event.
     */
    public UpdateEndTimeCommand(int index, LocalDateTime newEndTime) {
        super(index);
        this.newEndTime = newEndTime;
    }

    /**
     * Executes the command on the bot.
     * @param bot The bot to execute this command on.
     * @return true, as it allows the programme to continue.
     */
    @Override
    public boolean execute(Duke bot) {
        bot.updateEndTime(super.index, this.newEndTime);
        return true;
    }
}
