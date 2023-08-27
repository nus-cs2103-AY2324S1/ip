public class ListCommand extends Command {
  public ListCommand(Printer out, TaskList taskList) {
    super(out, taskList);
  }

  @Override
  public void execute() {
    Object toPrint[] = new Object[taskList.size() + 1];
    toPrint[0] = "Here are the tasks in your list:";
    for (int i = 1; i <= taskList.size(); ++i) {
      toPrint[i] = String.format("%d.%s", i, taskList.getTask(i));
    }
    out.print(toPrint);
  }
}
