package ben;

public class UnmarkCommand extends Command {
    private Task task;

    public UnmarkCommand(Task task) {
        this.task = task;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        task.unmark();
        Ui.displayMessage("\n" +
                "Okay! This task is not completed\n" + task + "\n");
    }
}
