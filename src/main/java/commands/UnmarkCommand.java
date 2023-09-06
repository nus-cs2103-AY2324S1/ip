package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;

public class UnmarkCommand extends Command {

    public UnmarkCommand() {
        super();
    }

    public void execute(TaskList<Task> tasks, Ui ui, boolean marked, boolean load) throws DukeException {
        int index = Integer.parseInt(ui.get(1)) - 1;
        if (index >= tasks.size()) {
            throw new DukeException();
        }
        Task job = (Task) tasks.get(index);
        job = job.unmark();
        tasks.set(index, job);
        System.out.println(ui.showLine() + "\n" +
                "OK, I've marked this task as not done yet:\n" + job.toString() + "\n"
                + ui.showLine());
    }
}
