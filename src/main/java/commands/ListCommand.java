package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, boolean marked, boolean load) {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list");
        for (int i = 0; i < tasks.size(); i++) {
            Task job = (Task) tasks.get(i);
            System.out.println(String.format("%d. %s", i + 1, job.toString()));
        }
        System.out.println("____________________________________________________________");
    }
}
