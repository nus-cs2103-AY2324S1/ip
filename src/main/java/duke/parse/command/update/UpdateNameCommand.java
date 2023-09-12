package duke.parse.command.update;

import duke.Duke;

/**
 * Represents a command to update the name of a task.
 */
public class UpdateNameCommand extends UpdateCommand {
    private final String newName;

    public UpdateNameCommand(int index, String newName) {
        super(index);
        this.newName = newName;
    }

    /**
     * Executes the command.
     * @param bot The bot to execute this command on.
     * @return true, as this allows the programme to continue.
     */
    @Override
    public boolean execute(Duke bot) {
        bot.updateName(super.index, this.newName);
        return true;
    }
}
