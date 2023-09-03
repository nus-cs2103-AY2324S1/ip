package duke.command;
import java.io.IOException;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * UnmarkCommand marks the task index specified as Uncompleted
 */

public class UnmarkCommand extends Command {
    /**
     * Indicates the index to unmark the task as uncompleted
     */
    private final int taskNum;
    /**
     * Constructor to make the UnmarkCommand class
     * @param taskNum indicates the index to mark the task as uncompleted
     */
    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the UnmarkCommand of the specified task
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference to the Storage class
     * @throws DukeException
     */

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
