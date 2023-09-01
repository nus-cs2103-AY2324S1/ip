public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    private int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.markTaskAsNotDone(taskNum);
        ui.showUnmarkTask(task);
    }
}
