public class DeleteCommand extends Command {
  private String s;
  private SaveFile saveFile;

  public DeleteCommand(Printer out, TaskList taskList, String name, SaveFile saveFile) {
    super(out, taskList);
    s = name;
    this.saveFile = saveFile;
  }

  @Override
  public void execute() {
    Task task;
    try {
      int i = Integer.parseInt(s); // 1-indexed
      task = taskList.getTask(i);
      taskList.deleteTask(i);
    } catch (NumberFormatException e) {
      throw new DukeException(String.format(DukeException.ARGUMENT_MUST_BE_NUM, DELETE));
    }

    out.print("Noted. I've removed this task:", task, taskList.getNumberOfTasks());
    saveFile.saveToSaveFile(taskList);
  }
}
