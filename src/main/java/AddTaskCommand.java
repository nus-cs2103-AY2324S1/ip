public class AddTaskCommand extends Command {
	private Task task;

	public AddTaskCommand(Printer out, TaskList taskList, Task task) {
		super(out, taskList);
		this.task = task;
	}

	@Override
	public void execute() {
		taskList.addTask(task);
		out.print("Got it. I've added this task:", task, taskList.getNumberOfTasks());
	}
}
