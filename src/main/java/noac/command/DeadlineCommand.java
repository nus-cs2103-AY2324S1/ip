package noac.command;

import noac.Storage;
import noac.TaskList;
import noac.Ui;
import noac.task.Deadline;

import java.time.LocalDateTime;

/**
 * For executing the deadline command.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDateTime by;

    /**
     * Create the deadline command with the description and by time.
     *
     * @param description description of the deadline.
     * @param by when the deadline is by.
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Create a Deadline, store it in Tasklist and save it.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){

        Deadline d = new Deadline(this.description, this.by);

        tasks.addTask(d);

        ui.showAddTask(d, tasks.size());

        storage.save(tasks);
    }
}
