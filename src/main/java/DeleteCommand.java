public class DeleteCommand extends Command {
  private String s;

  public DeleteCommand(Printer out, TaskList taskList, FileIO savefile, String name) {
    super(out, taskList, savefile);
		this.s = name;
  }

  @Override
  public void action() {
    Task task;
    try {
      int i = Integer.parseInt(s); // 1-indexed
      task = taskList.getTask(i);
      taskList.deleteTask(i);
    } catch (NumberFormatException e) {
			throw new ArgumentMustBeNumException(DELETE);
    }

    out.print("Noted. I've removed this task:", task.toString(), taskList.getNumberOfTasks());
		save();
  }
}
