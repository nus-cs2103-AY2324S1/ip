public class MarkCommand extends Command {
	private String s;
	private SaveFile saveFile;

	public MarkCommand(Printer out, TaskList taskList, String s, SaveFile saveFile) {
		super(out, taskList);
		this.s=s;
		this.saveFile = saveFile;
	}

	@Override
	public void execute() {
		Task task;
		try {
			task = taskList.getTask(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			throw new DukeException(String.format(DukeException.ARGUMENT_MUST_BE_NUM, MARK));
		}

		task.mark();

		out.print("Nice! I've marked this task as done:", task);
		saveFile.saveToSaveFile(taskList);
	}
}
