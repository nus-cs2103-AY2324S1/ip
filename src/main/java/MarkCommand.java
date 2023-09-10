public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private static final String MESSAGE = "Nice! I've marked this task as done:\n  %1$s";
    private final int index;

    public MarkCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.mark(this.index);
        ui.showResult(String.format(MESSAGE, t));
    }
}
