package duke.command;

import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.List;
import java.util.Map;

public class ByeCommand extends Command {

    public ByeCommand(Map<String, Object> args) {
        super("bye", args);
    }

    @Override
    public List<CommandElement> getCommandElements() {
        return List.of();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.print("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
