package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeadlineCommand implements Command{
    private String details;

    public DeadlineCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (details == "") {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
        } else {
            String[] partDeadline = details.split("/by");
            Task curr = new Deadline(partDeadline[0], partDeadline[1].trim());
            tasks.add(curr);
            ui.sendMessage("Got it. I've added this task:\n" + "\t" + curr.toString() + "\n"
                    + "Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            storage.editData(tasks);
        }
    }

    @Override
    public void loadTask(TaskList tasks) {
        String[] partDeadline = details.split("/by");
        Task curr = new Deadline(partDeadline[0], partDeadline[1]);
        tasks.add(curr);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
