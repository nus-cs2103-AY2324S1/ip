public class MarkCommand extends Command {
	private Task task;

	public MarkCommand(String name) {
		try {
			task = Task.getTask(Integer.parseInt(name));
		} catch (NumberFormatException e) {
			throw new DukeException(String.format(DukeException.ARGUMENT_MUST_BE_NUM, MARK_COMMAND));
		}
	}

	@Override
	public void execute(Printer out) {
		task.mark();

		out.print("Nice! I've marked this task as done:", task);
	}
}
