package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;

/**
 * The command will search for tasks given a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the provided keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String formattedTasks = tasks.findTasksFormatted(keyword);
        if (formattedTasks.isEmpty()) {
            ui.bufferMessage("No tasks matching the keyword in the description was found.");
        } else {
            ui.bufferMessage("Here are the matching tasks in your list:");
            ui.bufferMessage(formattedTasks);
        }
    }
}
