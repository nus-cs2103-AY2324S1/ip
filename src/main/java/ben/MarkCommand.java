package ben;

public class MarkCommand extends Command {
    Task task;

    public MarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        task.mark();
        Ui.displayMessage("\n" +
                "Nice! This task is completed\n" + task + "\n");
    }
}
