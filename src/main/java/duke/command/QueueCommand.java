package duke.command;

import duke.object.TaskList;
import duke.object.task.Task;
import duke.object.task.Deadline;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueueCommand extends Command {

    public QueueCommand(Map<String, Object> args) {
        super("queue", args);
    }

    @Override
    public List<CommandElement> getCommandElements() {
        return List.of();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Deadline> deadlines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        ui.print(String.format("Here is the queue of deadlines:\n%s", ui.stringifyList(deadlines)));
    }

    @Override
    public boolean isExit() {
        return false;
    }

}