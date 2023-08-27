package robert.command;

import java.time.LocalDate;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Deadline;
import robert.task.Event;
import robert.task.Task;
import robert.task.TaskList;
import robert.task.ToDo;
import robert.ui.Ui;

public class AddCommand extends Command {

    private final Task task;

    public AddCommand(String description) {
        this.task = new ToDo(description);
    }

    public AddCommand(String description, LocalDate fromDate, LocalDate toDate) throws RobertException {
        this.task = new Event(description, fromDate, toDate);
    }

    public AddCommand(String description, LocalDate byDate) {
        this.task = new Deadline(description, byDate);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.addTask(this.task);
        ui.showMessage("Got it. I have added this task:\n  " + this.task
                + "\nNow you have " + tasks.getTaskCount() + " "
                + (tasks.getTaskCount() > 1 ? "tasks" : "task")
                + " in the list.");
    }

}
