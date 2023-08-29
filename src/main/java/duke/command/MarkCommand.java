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

public class MarkCommand extends Command {

    public MarkCommand(Map<String, Object> args) {
        super("mark", args);
    }

    @Override
    public List<CommandElement> getCommandElements() {
        return List.of(new IndexArgument("index"));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int idx = (int) ((Integer) this.args.get("index"));
        tasks.mark(idx);
        ui.print(String.format("Nice! I've marked this task as done:\n  %s", tasks.access(idx).toString()));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
