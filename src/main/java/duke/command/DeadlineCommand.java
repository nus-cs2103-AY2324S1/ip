package duke.command;

import duke.exception.ChatException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

/**
 * Prompts the programme to create a new task with deadline.
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
     * @param tasks List of task stored by the programme.
     * @param ui Responses to be shown to user.
     * @param storage Saves the list of task to be accessed in the future.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deadline = new Deadline(this.description, this.by);
            tasks.addTask(deadline);
            ui.formatTaskResponse(deadline, tasks);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    /**
     * Checks if command will end programme.
     * @return False.
     */
    public boolean isExit() {
        return false;
    };
}
