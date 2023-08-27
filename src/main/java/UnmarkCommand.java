public class UnmarkCommand extends Command {
  private String s;
	private SaveFile saveFile;

  public UnmarkCommand(Printer out, TaskList taskList, String s, SaveFile saveFile) {
    super(out, taskList);
    this.s = s;
		this.saveFile = saveFile;
  }

  @Override
  public void execute() {
		Task task;
    try {
      task = taskList.getTask(Integer.parseInt(s));
    } catch (NumberFormatException e) {
      throw new DukeException(String.format(DukeException.ARGUMENT_MUST_BE_NUM, UNMARK));
    }
    task.unmark();

    out.print("Ok, I've marked this task as not done yet", task);
		saveFile.saveToSaveFile(taskList);
  }
}
