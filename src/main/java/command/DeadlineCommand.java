package command;

import duke.Storage;
import duke.Ui;

import task.Deadline;
import task.TaskList;

import java.time.LocalDateTime;

/**
 * Adds a deadline, which has a description and a date/time to do by, to the todo list
 */
public class DeadlineCommand extends Command {

    /** Description of the task */
    protected String description;

    /**
     * Deadline that the task is to be done by
     */
    protected LocalDateTime by;
    public static final String COMMAND_WORD = "deadline";
    public static final String MESSAGE_SUCCESS = " Got it. I've added this task:\n";

    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Adds the deadline task to the given TaskList, and saves the current TaskList in
     * the specified Storage file
     *
     * @param tasks TaskList which contains an ArrayList of tasks
     * @param ui Text Ui that the user interacts with
     * @param storage File path where the tasks are stored
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.addTask(deadline);
        storage.writeToFile(tasks.getList());
        ui.showMessage(MESSAGE_SUCCESS + "     " + deadline
                + "\n Now you have " + tasks.getSize() + " tasks in the list");
    }
}
