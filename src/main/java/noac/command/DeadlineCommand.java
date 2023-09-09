package noac.command;

import noac.util.Storage;
import noac.util.TaskList;
import noac.util.Ui;
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
    public String execute(TaskList tasks, Ui ui, Storage storage){

        Deadline d = new Deadline(this.description, this.by);

        tasks.addTask(d);

        storage.save(tasks);

        return ui.showAddTask(d, tasks.size());
    }
}
