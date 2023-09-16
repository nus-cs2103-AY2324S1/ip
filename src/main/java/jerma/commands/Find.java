package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

/**
 * Find class, is a Command that finds tasks from a search keyword
 */
public class Find extends Command {
    private String keyword;

    /**
     * Create a Find command
     *
     * @param ui      Ui instance
     * @param tasks   Current tasklist
     * @param keyword Search keyword
     */
    public Find(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        this.keyword = keyword;
    }

    @Override
    public String execute() {
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                results.add(task);
            }
        }
        return ui.listTasks(results);
    }

}
