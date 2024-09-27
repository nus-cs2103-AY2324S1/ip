package duke.command;

import java.time.LocalDate;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Prompts the program to create a new task with deadline.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate by;

    /**
     * Constructor for the class DeadlineCommand.
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }
    /**
     * Creates a new task with deadline and stores it in tasks,
     * shows appropriate response to user and saves task to storage.
     * @param tasks List of task stored by the program.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deadline = new Deadline(this.description, this.by);
            tasks.addTask(deadline);
            storage.saveList(tasks);
            return ui.addTaskResponse(deadline, tasks);
        } catch (ChatException e) {
            return ui.showLoadingError(e);
        }
    };
}
