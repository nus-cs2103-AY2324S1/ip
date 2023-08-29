package duke.command;
import java.io.IOException;
import duke.task.*;
import duke.helper.*;
public class DeleteCommand extends Command {
    int tasknum;
    public DeleteCommand(int tasknum) {
        this.tasknum = tasknum;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.deleteTask(tasknum);
            store.save(tasks);
        }
        catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
