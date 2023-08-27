public class AddTaskCommand extends Command {
	private Task task;
	private SaveFile saveFile;

	public AddTaskCommand(Printer out, TaskList taskList, Task task, SaveFile saveFile) {
		super(out, taskList);
		this.task = task;
		this.saveFile = saveFile;
	}

	@Override
	public void execute() {
		taskList.addTask(task);
		out.print("Got it. I've added this task:", task, taskList.getNumberOfTasks());

		saveFile.saveToSaveFile(taskList);
	}
}
