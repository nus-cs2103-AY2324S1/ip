package duke.command;

import java.util.List;
import java.util.Map;

import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.IndexArgument;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to mark a task as complete.
 */
public class MarkCommand extends Command {

    /**
     * Constructs MarkCommand.
     *
     * @param args The arguments entered by the user.
     */
    public MarkCommand(Map<String, Object> args) {
        super("mark", args);
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert this.args.get("index") instanceof Integer;
        int idx = (int) ((Integer) this.args.get("index"));
        tasks.mark(idx);
        ui.print(String.format("Nice! I've marked this task as done:\n  %s", tasks.access(idx).toString()));
    }

}
