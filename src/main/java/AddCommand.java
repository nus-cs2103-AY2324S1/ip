public class AddCommand extends Command{
    private Task task;
    public AddCommand (Task task) {
        super(false);
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list");
    }
}
