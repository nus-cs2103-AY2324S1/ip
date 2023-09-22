package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

import java.time.LocalDateTime;

/**
 * A Deadline Command
 */
public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime dateTime;

    /**
     * A constructor for the DeadlineCommand object
     * @param description description of the task that has a deadline
     * @param dateTime the time that the task should be done by
     */
    public DeadlineCommand(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        Deadline deadlineTask = new Deadline(this.description, this.dateTime);
        taskList.addTask(deadlineTask);
        storage.saveCurrentTasks(taskList.getTaskArray());
        ui.showMessage("Got it. I've added this task:\n" + deadlineTask.toString()
                + "\nNow you have " + taskList.getSize() + " tasks in the list.");
    }
}
