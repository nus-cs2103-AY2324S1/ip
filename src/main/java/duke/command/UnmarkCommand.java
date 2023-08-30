package duke.command;
import java.io.IOException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.DukeException;
import duke.helper.Ui;
import duke.helper.Storage;

/**
 * UnmarkCommand marks the task index specified as Uncompleted
 */

public class UnmarkCommand extends Command {
    /**
     * Indicates the index to unmark the task as uncompleted
     */
    int taskNum;
    /**
     * Constructor to make the UnmarkCommand class
     * @param taskNum indicates the index to mark the task as uncompleted
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            Task task = tasks.getTask(taskNum);
            tasks.unmarkTask(taskNum);
            store.save(tasks);
            ui.printUnmarkTask(taskNum, task);
        } catch (IOException e) {
            throw new DukeException(" unable to locate local file!");
        }

    }
}
