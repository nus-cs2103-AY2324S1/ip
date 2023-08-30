package duke.command;
import java.io.IOException;
import duke.task.DukeException;
import duke.task.TaskList;
import duke.helper.Ui;
import duke.helper.Storage;

/**
 * MarkClass marks the task index specified as Completed
 */
public class MarkCommand extends Command{
    /**
     * Indicates the index to mark the task as completed
     */
    int taskNum;

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
            tasks.markTask(taskNum);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
