package duke.command;

import duke.Ui;
import duke.Storage;
import duke.task.*;

/**
 * A command of what to do when user input is not understood
 */
public class DefaultCommand extends Command {
    protected String response;
    public DefaultCommand(String response) {
        super();
        this.response = response;
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
