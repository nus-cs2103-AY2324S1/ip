package duke.commands;

import duke.records.ChatRecord;

public class InvalidCommand extends Command {

    private String prefix = "Invalid Command! ";
    private String errMessage;

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
