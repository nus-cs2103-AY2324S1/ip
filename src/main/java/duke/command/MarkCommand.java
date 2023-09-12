package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The MarkCommand class represents a command to mark the specified task in the list as done.
 */
public class MarkCommand extends Command {
    private final int TASK_NUMBER;

    /**
     * Construcst a MarkCommand object.
     *
     * @param taskNumber The task number corresponding to the task, to be marked done.
     */
    public MarkCommand(int taskNumber) {
        TASK_NUMBER = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (TASK_NUMBER > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + TASK_NUMBER + " does not exist.");
        }

        String message = ui.showTaskMarkedAsDone(taskList.getTask(TASK_NUMBER));
        storage.save(taskList.getList(), ui);
        return message;
    }
}
