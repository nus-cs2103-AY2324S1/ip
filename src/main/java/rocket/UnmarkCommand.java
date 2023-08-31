package rocket;

public class UnmarkCommand extends Command {
    private int taskNumber;
    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber);
        task.markAsUndone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }
}
