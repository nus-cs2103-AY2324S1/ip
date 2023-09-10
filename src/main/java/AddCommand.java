public class AddCommand extends Command {
    private static final String MESSAGE =
            "Got it. I've added this task:\n  %1$s\nNow you have %2$d tasks in the list.";
    private final Task toAdd;

    public AddCommand(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.toAdd);
        ui.showResult(String.format(MESSAGE, this.toAdd, tasks.size()));
    }
}
