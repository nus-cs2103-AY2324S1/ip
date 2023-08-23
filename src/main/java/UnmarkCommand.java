public class UnmarkCommand extends Command {
	private Task task;

	public UnmarkCommand(String name) {
		try {
			task = Task.getTask(Integer.parseInt(name));
		} catch (NumberFormatException e) {
			throw new DukeException(String.format(DukeException.ARGUMENT_MUST_BE_NUM, UNMARK_COMMAND));
		}
	}

	@Override
	public void execute(Printer out) {
		task.unmark();

		out.print("Ok, I've marked this task as not done yet", task);
	}
}
