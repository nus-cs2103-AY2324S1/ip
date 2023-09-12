package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

public class FindCommand extends Command {

    public FindCommand() {
        super();
    }

    public String execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) {
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
