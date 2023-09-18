package duchess.command;

import duchess.Task;
import duchess.TaskList;
import duchess.Ui;

import java.util.ArrayList;

/**
 * Class representing an Unrecognized command.
 */
public class UnrecognizedCommand extends Command {

    /**
     * Prints the unrecognized error message.
     *
     * @param userInput - the user's input.
     * @return            Duchess' response to the command.
     */
    @Override
    public String execute(String userInput, TaskList tasks) {
        return Ui.duchessFormat("(´；ω；`) Oopsies... I don't know what that means ;-;");
    }
}
