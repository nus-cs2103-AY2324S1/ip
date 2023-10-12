package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import duke.object.TaskList;
import duke.object.task.Deadline;
import duke.object.task.Task;
import duke.parser.element.CommandElement;
import duke.storage.Storage;
import duke.util.Formatter;

/**
 * Command to list all deadlines in sorted order.
 */
public class QueueCommand extends Command {

    /**
     * Constructs QueueCommand.
     *
     * @param args The arguments entered by the user.
     */
    public QueueCommand(Map<String, Object> args) {
        super("queue", args);
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
        List<Deadline> deadlines = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        return String.format("Here is the queue of deadlines:\n%s", Formatter.stringifyList(deadlines));
    }

}
