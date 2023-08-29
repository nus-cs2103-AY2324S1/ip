package duke.command;
import java.io.IOException;
import duke.task.TaskList;
import duke.task.*;
import duke.helper.*;
public class UnmarkCommand extends Command {
    /**
     * Indicates the index to unmark the task as uncompleted
     */
    int tasknum;
    /**
     * Constructor to make the UnmarkCommand class
     * @param tasknum indicates the index to mark the task as uncompleted
     */
    public UnmarkCommand(int tasknum) {
        this.tasknum = tasknum;
    }

    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.unmarkTask(tasknum);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" unable to locate local file!");
        }


    }
}
