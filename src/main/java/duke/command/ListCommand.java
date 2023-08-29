package duke.command;

import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Command to list all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     * 
     * @param args The arguments entered by the user.
     */
    public ListCommand(Map<String, Object> args) {
        super("list", args);
    }

    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(String.format("Here are the tasks in your list:\n%s", ui.stringifyList(tasks)));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
