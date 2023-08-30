import java.io.IOException;

public class Command {
    public Task task;
    public Command(Task task) {
        this.task = task;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
    }
    public boolean isExit() {
        return false;
    }
}
