package duke.command;

import java.util.List;
import java.util.Map;

import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.util.Formatter;

/**
 * Command to list all current tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs ListCommand.
     *
     * @param args The arguments entered by the user.
     */
    public ListCommand(Map<String, Object> args) {
        super("list", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of();
    }

    /**
     * @inheritdoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return String.format("Here are the tasks in your list:\n%s", Formatter.stringifyList(tasks));
    }

}
