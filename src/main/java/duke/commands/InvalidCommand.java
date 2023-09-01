package duke.commands;

import duke.records.ChatRecord;

/**
 * A placeholder command for invalid commands.
 * @author Toh Li Yuan (A0255811H)
 */
public class InvalidCommand extends Command {

    private String prefix = "Invalid Command! ";
    private String errMessage;

    /**
     * Creates a command for invalid commands.
     *
     * @param errMessage the error message to show to the user.
     */
    public InvalidCommand(String errMessage) {
        this.errMessage = errMessage;
    }
    @Override
    public void init(ChatRecord records) { }

    /**
     * Executes the created Invalid Command.
     *
     * @return The error string to be displayed as feedback to the user.
     */
    @Override
    public String execute() {
        return prefix + errMessage;
    }

    @Override
    public String toString() {
        return prefix + errMessage;
    }
}
