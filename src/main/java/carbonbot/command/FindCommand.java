package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.task.Task;

/**
 * The command will search for tasks given a keyword
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand object with the provided keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the matching tasks in your list:");

        // Print tasks that contains the keyword
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(keyword)) {
                ui.showMessage(i + "." + t.toString());
            }
        }
    }
}
