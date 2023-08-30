public class AddCommand extends Command {

    private final Task task;

    public AddCommand(String description) {
        this.task = new ToDo(description);
    }

    public AddCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    public AddCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.showMessage("Got it. I've added this task:\n" + task + "\nNow you have "
                + tasks.getTotalTask() +" tasks in the list.");
    };

    @Override
    public boolean isExit() {
        return false;
    };
}
