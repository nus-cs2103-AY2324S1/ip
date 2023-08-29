public class UnmarkCommand extends Command {
  private String s;

  public UnmarkCommand(Printer out, TaskList taskList, FileIO savefile, String s) {
    super(out, taskList, savefile);
    this.s = s;
  }

  @Override
  public void action() {
    Task task;
    try {
      task = taskList.getTask(Integer.parseInt(s));
    } catch (NumberFormatException e) {
			throw new ArgumentMustBeNumException(UNMARK);
    }
    task.unmark();

    out.print("Ok, I've marked this task as not done yet", task.toString());
		save();
  }
}
