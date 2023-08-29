import java.io.IOException;

public class MarkCommand extends Command{
    int tasknum;
    public MarkCommand(int tasknum) {
        this.tasknum = tasknum;
    }
    @Override
    void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        try {
            tasks.markTask(tasknum);
            store.save(tasks);
        } catch (IOException e) {
            throw new DukeException(" umable to locate local file!");
        }
    }
}
