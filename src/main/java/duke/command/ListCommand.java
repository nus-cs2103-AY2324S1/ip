package duke.command;

import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ListCommand extends Command {

    public ListCommand(Map<String, Object> args) {
        super("list", args);
    }

    @Override
    public List<CommandElement> getCommandElements() {
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
