package commands;

import functional.DukeException;
import functional.TaskList;
import functional.Ui;
import tasks.Task;


public class DeleteCommand extends commands.Command {

    public DeleteCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, boolean marked, boolean load) throws DukeException {
        int index = Integer.parseInt(ui.get(1)) - 1;
        if (index >= tasks.size()) {
            throw new DukeException();
        }
        Task job = (Task) tasks.get(index);
        tasks.remove(index);
        System.out.println("____________________________________________________________\n" +
                "Noted, I've removed this task:\n" + job.toString() + "\n" +
                String.format("Now you have %d tasks in the list\n", tasks.size()) +
                "____________________________________________________________");
    }
}
