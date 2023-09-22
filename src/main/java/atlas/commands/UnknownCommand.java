package atlas.commands;

import atlas.components.Storage;
import atlas.components.TaskList;

/**
 * Command used to handle invalid commands (e.g. bad parsing)
 */
public class UnknownCommand extends Command {
    private final String errorMsg;

    /**
     * Constructs an UnknownCommand object with the default error message
     */
    public UnknownCommand() {
        errorMsg = "Your words are meaningless, mortal. Why don't you ask the "
                + "Olympians for \"help\"?";
    }

    /**
     * Constructs an UnknownCommand object with a specified error message
     * @param errorMsg Error message to print
     */
    public UnknownCommand(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return errorMsg;
    }
}
