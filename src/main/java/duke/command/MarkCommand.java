package duke.command;
import java.io.IOException;
import duke.task.DukeException;
import duke.task.TaskList;
import duke.helper.Ui;
import duke.helper.Storage;

public class MarkCommand extends Command {
    int tasknum;
    public MarkCommand(int tasknum) {
        this.tasknum = tasknum;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.markTask(tasknum);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
