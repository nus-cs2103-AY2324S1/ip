import duke.exceptions.TaskIndexOutOfBoundsException;

public class DeleteCommand extends Command {
    protected int taskIndex;
    public DeleteCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getSize()) {
                throw new TaskIndexOutOfBoundsException("Invalid task index");
            }
            taskList.deleteTask(taskIndex);
            System.out.println(ui.format_response("Task successfully deleted"));
        } catch (TaskIndexOutOfBoundsException e) {
            System.out.println(ui.format_response("Invalid task index. Please provide a valid index."));
        }
    }
}
