package duke.command;
import java.io.IOException;
import duke.task.TaskList;
import duke.task.*;
import duke.helper.*;
public class UnmarkCommand extends Command {
    int tasknum;
    public UnmarkCommand(int tasknum) {
        this.tasknum = tasknum;
    }

    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.unmarkTask(tasknum);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }


    }
}
