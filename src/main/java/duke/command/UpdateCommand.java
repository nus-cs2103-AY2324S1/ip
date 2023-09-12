package duke.command;

import java.time.LocalDateTime;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Represents a command to update an event.
 */
public class UpdateCommand extends Command {
    private int taskIndex;

    private String newDescription;
    private LocalDateTime newDateTime;

    /**
     * Constructs an UpdateCommand with the provided task index.
     *
     * @param taskIndex The index of the task to be updated.
     * @param newDescription The new description of the event.
     * @param newDateTime The new date-time of the event.
     */
    public UpdateCommand(int taskIndex, String newDescription, LocalDateTime newDateTime) {
        this.taskIndex = taskIndex;
        this.newDescription = newDescription;
        this.newDateTime = newDateTime;
    }

    @SuppressWarnings("checkstyle:EmptyLineSeparatorCheck")
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task taskToUpdate = taskList.getTaskAtIndex(taskIndex);

            if (taskToUpdate instanceof Todo && newDescription != null) {
                taskToUpdate.setDescription(newDescription);
            }

            if (!(taskToUpdate instanceof Todo)) {
                if (newDescription != null) {
                    taskToUpdate.setDescription(newDescription);
                }

                if (newDateTime != null) {
                    taskToUpdate.setDateTime(newDateTime);
                }
            }

            return ui.showTaskUpdated(taskToUpdate);
        } catch (InvalidTaskIndexException e) {
            return ui.showDukeException(e);
        }
    }
}
