package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Deadline;
import nobita.task.Event;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.task.ToDo;
import nobita.ui.Ui;

/**
 *  Class that encapsulates AddCommand which extends from Command.
 *
 *  @author Zheng Chenglong
 *
 */
public class AddCommand extends Command {

    /** The task to be added */
    private final Task task;

    /**
     * Constructor AddCommand for ToDo task.
     *
     * @param description The description of task.
     */
    public AddCommand(String description) {
        this.task = new ToDo(description);
    }

    /**
     * Constructor AddCommand for Deadline task.
     *
     * @param description The description of task.
     * @param by The due date of task.
     */
    public AddCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    /**
     * Constructor AddCommand for Event task.
     *
     * @param description The description of task.
     * @param from  The start date for task.
     * @param to The end data for task.
     */
    public AddCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }

    /**
     *  Command that executes the task to be added.
     *
     *  @param tasks Contains all current tasks.
     *  @param ui Ui for interacting with user.
     *  @param storage Storage that the data file is stored in.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showMessage("Got it. I've added this task:\n" + task + "\nNow you have "
                + tasks.getTotalTask() +" tasks in the list.");
    };

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
