import java.io.IOException;

public class UnmarkCommand extends Command {
    int tasknum;
    public UnmarkCommand(int tasknum) {
        this.tasknum = tasknum;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.unmarkTask(tasknum);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }


    }
}
