package duke.command;

import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.IndexArgument;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Command to remove an task from the list.
 */
public class RemoveCommand extends Command {

    /**
     * Constructor for RemoveCommand.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int idx = (int) ((Integer) this.args.get("index"));
        ui.print(String.format("Noted. I've removed this task:\n    %s\n%s",
                tasks.delete(idx).toString(), ui.getTaskCount(tasks.size())));
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
