package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.UiManager;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;

/**
 * This is a command to create a deadline task.
 */
public class DeadlineCommand extends Command {

    private String description;
    private LocalDate by;

    /**
     * Constructs deadline command.
     *
     * @param description Description of the deadline task.
     * @param by Deadline of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(TaskList taskList, UiManager uiManager, Storage storage) throws DukeException {
        assert taskList != null: "Task list should not be null";
        assert uiManager != null: "UI manager should not be null";
        assert storage != null: "Storage object should not be null";
        Task temp = new Deadline(description, by);
        taskList.addTask(temp);
        storage.save(taskList);
        return uiManager.getAddTaskMessage(temp, taskList);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof DeadlineCommand) {
            DeadlineCommand temp = (DeadlineCommand) obj;
            if (temp.description.equals(temp.description) && this.by.equals(temp.by)) {
                return true;
            }
        }
        return false;
    }
}
