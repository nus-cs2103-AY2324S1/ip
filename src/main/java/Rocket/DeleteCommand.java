package Rocket;

public class DeleteCommand extends Command{
    public DeleteCommand() {
        super(false);
    }

    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list");
    }


}
