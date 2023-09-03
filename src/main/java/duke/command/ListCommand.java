package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * The ListCommand is a command that
 * displays the current tasks on screen.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return(tasks.display());
    }
}
