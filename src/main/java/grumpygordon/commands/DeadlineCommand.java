package grumpygordon.commands;

import java.time.LocalDateTime;

import grumpygordon.storage.Storage;
import grumpygordon.tasks.Deadline;
import grumpygordon.tasks.TaskList;
import grumpygordon.ui.Ui;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    /**
     * Description of the deadline task.
     */
    private final String description;

    /**
     * Deadline of the deadline task.
     */
    private final LocalDateTime by;

    /**
     * Constructor of DeadlineCommand.
     * @param description Description of the deadline task
     * @param by Deadline of the deadline task
     */
    public DeadlineCommand(String description, LocalDateTime by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command.
     * @param tasks The list of tasks
     * @param ui The user interface
     * @param storage The storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(new Deadline(this.description, this.by, false));
        ui.showCommandMessage("     Deadline task added to list\n     "
                + tasks.getTask(tasks.size() - 1).toString() + "\n");
        storage.saveTasks(tasks);
    }
}
