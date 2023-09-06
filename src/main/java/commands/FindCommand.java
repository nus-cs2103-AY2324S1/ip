package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

public class FindCommand extends Command {

    public FindCommand() {
        super();
    }

    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) {
        String keyword = ui.get(1);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task job = (Task) tasks.get(i);
            if (job.getContent().contains(keyword)) {
                sb.append(String.format("%d. %s", i + 1, job.toString()) + "\n");
            }
        }
        if (sb.length() >= 1){
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(ui.showLine() + "\n" +
                    "Here are the matching tasks in your list:");
            System.out.println(sb.toString());
            System.out.println(ui.showLine());
        } else {
            System.out.println(ui.showLine());
            System.out.println("No matching results");
            System.out.println(ui.showLine());
        }

    }
}
