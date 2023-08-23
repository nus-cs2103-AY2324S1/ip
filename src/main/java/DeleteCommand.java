public class DeleteCommand extends Command {
	private Task task;
	public int i;

	public DeleteCommand(String name) {
		try {
			i = Integer.parseInt(name);
			task = Task.getTask(i);
		} catch (NumberFormatException e) {
			throw new DukeException(String.format(DukeException.ARGUMENT_MUST_BE_NUM, DELETE_COMMAND));
		}
	}

	@Override
	public void execute(Printer out) {
		Task.deleteTask(i);

		out.print("Noted. I've removed this task:", task, Task.getNumberOfTasks());
	}
}
