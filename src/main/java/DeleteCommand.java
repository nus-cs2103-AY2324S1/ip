public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE =
            "Noted. I've removed this task:\n  %1$s\nNow you have %2$d tasks in the list.";
    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.delete(this.index);
        ui.showResult(String.format(MESSAGE, t, tasks.size()));
    }
}
