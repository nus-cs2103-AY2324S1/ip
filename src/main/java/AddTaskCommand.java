public class AddTaskCommand extends Command {
	private Task task;

	private AddTaskCommand(Task task) {
		this.task = task;
	}

	@Override
	public void execute(Printer out) {
		Task.addTask(task);
		out.print("Got it. I've added this task:", task, Task.getNumberOfTasks());
	}

	static AddTaskCommand AddTodoCommand(String description) {
		return new AddTaskCommand(new Todo(description));
	}

	static AddTaskCommand AddDeadlineCommand(String description, String by) {
		return new AddTaskCommand(new Deadline(description, by));
	}

	static AddTaskCommand AddEventCommand(String description, String from, String to) {
		return new AddTaskCommand(new Event(description, from, to));
	}
}
