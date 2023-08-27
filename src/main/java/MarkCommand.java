public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    private int taskIndex;

    public MarkCommand(String taskIndex) {
        this.taskIndex = Integer.parseInt(taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException("No such task exists.");
        }

        Task task = tasks.mark(taskIndex);

        storage.save(tasks);
        ui.showMessage(
                "Nice! I've marked this task as done:",
                "\t " + task
        );
    }
}
