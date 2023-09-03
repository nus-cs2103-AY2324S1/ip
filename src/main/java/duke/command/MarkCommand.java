package duke.command;
import java.io.IOException;

import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.Task;
import duke.task.TaskList;


/**
 * MarkClass marks the task index specified as Completed
 */
public class MarkCommand extends Command {
    /**
     * Indicates the index to mark the task as completed
     */
    private final int taskNum;

    /**
     * Constructor to make the MarkCommand class
     * @param taskNum indicates the index to mark the task as completed
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }
    /**
     * execute for ListCommand lists the tasks out
     * @param tasks takes in TaskList of tasks in MeowBot
     * @param ui the same object reference for the UI class
     * @param store reference
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            Task task = tasks.getTask(taskNum);
            tasks.markTask(taskNum);
            store.save(tasks);
            ui.printMarkTask(taskNum, task);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
