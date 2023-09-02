package ben;

public class DeleteCommand extends Command {
    private Task task;

    public DeleteCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        tasks.delete(task);
    }
}
