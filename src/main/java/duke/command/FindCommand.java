package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import duke.object.TaskList;
import duke.object.task.Task;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.StringArgument;
import duke.storage.Storage;
import duke.util.Formatter;

/**
 * Command to find all relevant tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructs ListCommand.
     *
     * @param args The arguments entered by the user.
     */
    public FindCommand(Map<String, Object> args) {
        super("find", args);
    }

    /**
     * @inheritdoc
     */
    @Override
    public List<CommandElement> getCommandElements() {
        return List.of(new StringArgument("key"));
    }

    /**
     * @inheritdoc
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert this.args.get("key") instanceof String;
        String key = (String) this.args.get("key");
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isRelated(key)) {
                filteredTasks.add(task);
            }
        }
        return String.format("Here are the relevant tasks:\n%s", Formatter.stringifyList(filteredTasks));
    }

}
