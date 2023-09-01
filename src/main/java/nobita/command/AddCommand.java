package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Deadline;
import nobita.task.Event;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.task.ToDo;
import nobita.ui.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(String description) {
        this.task = new ToDo(description);
    }

    public AddCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    public AddCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        tasks.addTask(this.task);
        ui.showMessage("Got it. I've added this task:\n" + task + "\nNow you have "
                + tasks.getTotalTask() +" tasks in the list.");
    };

    @Override
    public boolean isExit() {
        return false;
    };
}
