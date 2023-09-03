package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The UnknownCommand represents a Command that
 * the chatbot does not understand.
 */
public class UnknownCommand extends Command {

    public UnknownCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return(ui.showUnknownCommand());
    }
}
