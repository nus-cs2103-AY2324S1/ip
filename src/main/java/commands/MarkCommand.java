package commands;

import functional.TaskList;
import functional.Ui;
import tasks.Task;

public class MarkCommand extends commands.Command {

    public MarkCommand() {
        super();
    }

    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws functional.DukeException {
        int index = Integer.parseInt(ui.get(1)) - 1;
        if (index >= tasks.size()) {
            throw new functional.DukeException();
        }
        Task job = (Task) tasks.get(index);
        job = job.mark();
        tasks.set(index, job);
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as done:\n" + job.toString() + "\n" +
                "____________________________________________________________");
    }
}
