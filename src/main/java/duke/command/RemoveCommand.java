package duke.command;

import java.util.List;
import java.util.Map;

import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.IndexArgument;
import duke.storage.Storage;
import duke.util.Formatter;

/**
 * Command to remove an task from the list.
 */
public class RemoveCommand extends Command {

    /**
     * Constructs RemoveCommand.
     *
     * @param args The arguments entered by the user.
     */
    public RemoveCommand(Map<String, Object> args) {
        super("remove", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected List<CommandElement> getCommandElements() {
        return List.of(new IndexArgument("index"));
    }

    /**
     * @inheritdoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        assert this.args.get("index") instanceof Integer;
        int idx = (int) ((Integer) this.args.get("index"));
        return String.format("Noted. I've removed this task:\n    %s\n%s",
                tasks.delete(idx).toString(), Formatter.getTaskCount(tasks.size()));
    }

}
