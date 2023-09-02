public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.addTask(this.task);
        ui.say(String.format(
                "Adding the task:\n%s\nNow there are %d tasks in the list.",
                this.task.toString(),
                taskList.getSize()));
    }
}
