package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
import duke.Ui;
import task.Deadline;

import java.time.LocalDate;

/**
 * Represents a deadline command where when executed, adds a task with deadline to the task list.
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDate deadline;

    /**
     * Constructs a DeadlineCommand object using a description and deadline.
     *
     * @param description The description for the command.
     * @param deadline The deadline for the command.
     */
    public DeadlineCommand(String description, LocalDate deadline) {
        this.description = description;
        this.deadline = deadline;
    }
    
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskManager taskManager, DiskManager diskManager, Ui ui) throws DukeException {
        ui.printOutput(taskManager.addTask(new Deadline(description, deadline)));
        diskManager.saveToDisk(taskManager);
    }
}
