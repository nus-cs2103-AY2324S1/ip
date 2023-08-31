package duke.command;

import duke.object.TaskList;
import duke.object.task.Task;
import duke.object.task.Event;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.StringArgument;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Command to find all relevant tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructor for ListCommand.
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String key = (String) this.args.get("key");
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.isRelated(key)) {
                filteredTasks.add(task);
            }
        }
        ui.print(String.format("Here are the relevant tasks:\n%s", ui.stringifyList(filteredTasks)));
    }

    /**
     * @inheritdoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
