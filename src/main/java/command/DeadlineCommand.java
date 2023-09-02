package command;

import duke.DiskManager;
import duke.DukeException;
import duke.TaskManager;
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
    public String execute(TaskManager taskManager, DiskManager diskManager) throws DukeException {
        String res = taskManager.addTask(new Deadline(description, deadline));
        diskManager.saveToDisk(taskManager);
        return res;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof DeadlineCommand) {
            DeadlineCommand temp = (DeadlineCommand) other;
            return temp.deadline.equals(this.deadline) && temp.description.equals(this.description);
        }

        return false;
    }
}
