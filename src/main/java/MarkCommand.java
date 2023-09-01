public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.markTaskAsDone(taskNum);
        ui.showMarkTask(task);
    }
}
