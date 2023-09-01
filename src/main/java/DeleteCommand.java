public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatBuddyException {
        Task task = tasks.deleteTask(taskNum);
        ui.showTaskDeletion(task, tasks.getSize());
    }
}
