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
 * Command to mark a task as incomplete.
 */
public class UnmarkCommand extends Command {

    /**
     * Constructor for UnmarkCommand.
     *
     * @param args The arguments entered by the user.
     */
    public UnmarkCommand(Map<String, Object> args) {
        super("unmark", args);
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
        int idx = (int) ((Integer) this.args.get("index"));
        tasks.unmark(idx);
        ui.print(String.format("OK, I've marked this task as not done yet:\n  %s", tasks.access(idx).toString()));
    }

}
