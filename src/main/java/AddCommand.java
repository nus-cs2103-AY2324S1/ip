public class AddCommand extends Command{

    private String taskType;
    private String[] args;

    public AddCommand(String taskType, String[] args) {
        this.taskType = taskType;
        this.args = args;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        String action = tasks.addTask(taskType, args);
        ui.respondUser(action);
    }

    @Override
    public boolean isExit() {
        return false;
    };
}
