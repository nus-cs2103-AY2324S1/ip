public class AddTaskCommand extends Command {
	private Task task;

	private AddTaskCommand(Printer out, TaskList taskList, Task task) {
		super(out, taskList);
		this.task = task;
	}

	@Override
	public void execute() {
		taskList.addTask(task);
		out.print("Got it. I've added this task:", task, taskList.getNumberOfTasks());
	}

	static AddTaskCommand AddTodoCommand(Printer out, TaskList taskList, String description) {
		return new AddTaskCommand(out, taskList, new Todo(description));
	}

	static AddTaskCommand AddDeadlineCommand(Printer out, TaskList taskList, String description, String by) {
		return new AddTaskCommand(out, taskList, new Deadline(description, by));
	}

	static AddTaskCommand AddEventCommand(Printer out, TaskList taskList, String description, String from, String to) {
		return new AddTaskCommand(out, taskList, new Event(description, from, to));
	}
}
