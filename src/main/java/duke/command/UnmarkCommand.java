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

public class UnmarkCommand extends Command {

    public UnmarkCommand(Map<String, Object> args) {
        super("unmark", args);
    }

    @Override
    public List<CommandElement> getCommandElements() {
        return List.of(new IndexArgument("index"));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int idx = (int) ((Integer) this.args.get("index"));
        tasks.unmark(idx);
        ui.print(String.format("OK, I've marked this task as not done yet:\n    %s", tasks.access(idx).toString()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
