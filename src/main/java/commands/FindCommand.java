package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

/**
 * The Command that conducts the 'Find' operation
 */
public class FindCommand extends Command {

    /**
     * Constructs the FindCommand instance
     */
    public FindCommand() {
        super();
    }

    /**
     * Returns the list of tasks found based on the keyword given.
     *
     * @param tasks  The task list.
     * @param ui     The user interface.
     * @param status task marked or unmarked and whether the task is being loaded from memory
     */
    public String execute(TaskList<Task> tasks, Ui ui, boolean... status) {
        String keyword = ui.get(1);
        StringBuilder sb = new StringBuilder();
        sb.append(ui.showLine() + "\n");
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task job = tasks.get(i);
            if (job.getContent().contains(keyword)) {
                sb.append(String.format("%d. %s", i + 1, job.toString()) + "\n");
            }
        }
        sb.append(ui.showLine());
        return sb.toString();
    }
}
