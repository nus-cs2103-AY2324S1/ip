package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand implements Command{
    private final String details;

    public TodoCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (details.equals("")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        } else {
            Task curr = new Todo(details);
            tasks.add(curr);
            ui.sendMessage("Got it. I've added this task:\n" + "\t" + curr + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
            storage.editData(tasks);
        }
    }

    @Override
    public void loadTask(TaskList tasks) {
        Task curr = new Todo(details);
        tasks.add(curr);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
