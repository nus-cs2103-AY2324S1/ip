package jerma.commands;

import jerma.tasks.Task;
import jerma.utils.TaskList;
import jerma.utils.Ui;

public class Find extends Command {
    private String keyword;

    public Find(Ui ui, TaskList tasks, String keyword) {
        super(ui, tasks);
        this.keyword = keyword;
    }

    public String execute() {
        TaskList results = new TaskList();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                results.add(task);
            }
        }
        return this.ui.listTasks(results);
    }

}
