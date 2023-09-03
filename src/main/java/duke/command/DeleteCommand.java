package duke.command;
import java.io.IOException;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * DeleteCommand class thatn deletes the wanted task from the TaskList
 */

public class DeleteCommand extends Command {

    /**
     * taskNum indicates the index in the TaskList class to delete
     */
    private final int taskNum;

    /**
     * Constructor for the DeleteCommand class that deletes the index in the TaskList
     * @param taskNum indicates the index in the TaskList class to delete
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Executes the DeleteCommand to remove task to TaskList using the parameters
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     * @throws DukeException throws a DukeException which indicates no desc if no text left
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            Task task = tasks.getTask(taskNum);
            tasks.deleteTask(taskNum);
            store.save(tasks);
            ui.printDeleteTask(tasks.size(), task);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
